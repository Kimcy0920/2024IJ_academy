package board.common;

import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import board.board.dto.BoardFileDto;

@Component
public class FileUtils {
	
	public List<BoardFileDto> parseFileInfo(int boardIdx, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception{
		if(ObjectUtils.isEmpty(multipartHttpServletRequest)){
			return null;
		}
		
		List<BoardFileDto> fileList = new ArrayList<>();
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");  // 오늘 날짜로 업로드 파일을 저장할 폴더 생성
    	ZonedDateTime current = ZonedDateTime.now();
    	String path = "images/"+current.format(format);
    	File file = new File(path);
		if(file.exists() == false){
			file.mkdirs();
		}
		
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
		
		String newFileName, originalFileExtension, contentType;
		
		while(iterator.hasNext()){
			List<MultipartFile> list = multipartHttpServletRequest.getFiles(iterator.next());
			for (MultipartFile multipartFile : list){
				if(multipartFile.isEmpty() == false){
					contentType = multipartFile.getContentType();
					if(ObjectUtils.isEmpty(contentType)){
						break;
					}
					else{ // 타입 확인, 이미지만 가능하게 조건문을 씀
						if(contentType.contains("image/jpeg")) {
							originalFileExtension = ".jpg";
						}
						else if(contentType.contains("image/png")) {
							originalFileExtension = ".png";
						}
						else if(contentType.contains("image/gif")) {
							originalFileExtension = ".gif";
						}
						else{
							break;
						}
					}
					
					newFileName = Long.toString(System.nanoTime()) + originalFileExtension; // 파일 이름짓기, 중복되지 않는 임의의 이름
					BoardFileDto boardFile = new BoardFileDto();
					boardFile.setBoardIdx(boardIdx);
					boardFile.setFileSize(multipartFile.getSize());
					boardFile.setOriginalFileName(multipartFile.getOriginalFilename());
					boardFile.setStoredFilePath(path + "/" + newFileName);
					fileList.add(boardFile); // List<BoardFileDto> fileList = new ArrayList<>();
					
					file = new File(path + "/" + newFileName);
					multipartFile.transferTo(file);
				}
			}
		}
		return fileList;
	}
}
