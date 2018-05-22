package com.deep.api.resource;

import com.deep.api.authorization.annotation.Permit;
import com.deep.api.request.VideoPublishRequest;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.VideoPublish;
import com.deep.domain.service.VideoPublishService;
import com.deep.domain.util.DownloadUtil;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/video")
public class VideoPublishResource {

  final String saveVideoPath = "./video/";

  private final Logger logger = LoggerFactory.getLogger(VideoPublishResource.class);

  @Resource
  private VideoPublishService videoPublishService;

  @Permit(authorities = "add_video")
  @PostMapping(value = "/upload")
  public Response uploadVideo(@Valid VideoPublish videoPublish,
                              @RequestParam("file")MultipartFile file) {

    if (file.isEmpty()) {
      return Responses.errorResponse("请上传视频文件");
    }

    DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhMMss");
    Calendar calendar = Calendar.getInstance();
    String fileName = new StringBuilder(dateFormat.format(calendar.getTime()))
        .append("-")
        .append(file.getOriginalFilename())
        .toString();
    videoPublish.setFileName(fileName);

    String savePath = new StringBuilder(saveVideoPath)
        .append(fileName)
        .toString();

    File saveFile = new File(savePath);
    if (!saveFile.getParentFile().exists()) {
        saveFile.getParentFile().mkdirs();
    }

    try {
        BufferedOutputStream out = new BufferedOutputStream(
            new FileOutputStream(saveFile));
        out.write(file.getBytes());
        out.flush();
        out.close();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
        System.out.println("上传失败，" + e.getMessage());
    } catch (IOException e) {
        e.printStackTrace();
        System.out.println("上传失败，" + e.getMessage());
    }
    int isSuccess = videoPublishService.addVideoPublish(videoPublish);
    if (isSuccess == 1) {
      return Responses.successResponse();
    } else {
      File deleteFile = new File(savePath);
      deleteFile.delete();
      return Responses.errorResponse("添加失败");
    }
  }

  @GetMapping(value = "/down/{fileName}")
  public Response downFile(@PathVariable("fileName")String fileName,
                           HttpServletResponse httpServletResponse) {

    logger.info("invoke video/down/{}",fileName);

    String filePath = new StringBuilder(saveVideoPath)
        .append(fileName)
        .toString();
    OutputStream outputStream = new OutputStream() {
      @Override
      public void write(int b) throws IOException {

      }
    };

    try {
      if (DownloadUtil.testDownload(httpServletResponse, filePath, fileName, outputStream)) {
        return Responses.successResponse();
      } else {
        return Responses.errorResponse("下载失败");
      }
    } catch (IOException e) {
      return Responses.errorResponse(e.getMessage());
    }

  }

  @PostMapping("/find")
  public Response findVideoFile(@RequestBody VideoPublishRequest videoPublishRequest) {

    RowBounds rowBounds = new RowBounds(videoPublishRequest.getPage() * 10, videoPublishRequest.getPageSize());
    List<VideoPublish> videoPublishList = videoPublishService.selectVideoFile(videoPublishRequest, rowBounds);
    int size = videoPublishService.allTotal();
    Response response = Responses.successResponse();
    Map<String, Object> data = new HashMap<String, Object>();
    data.put("List",videoPublishList);
    data.put("size",size);
    response.setData(data);
    return response;
  }

  @Permit(authorities = "delete_video")
  @DeleteMapping("/{id}")
  public Response deleteVideoFile(@PathVariable(value = "id") int id) {
    int isSuccess = videoPublishService.deleteVideoFile(id);
    if (isSuccess == 1) {
      return Responses.successResponse();
    } else {
      return Responses.errorResponse("删除失败");
    }
  }
}
