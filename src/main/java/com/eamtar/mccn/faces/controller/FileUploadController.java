package com.eamtar.mccn.faces.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.eamtar.mccn.faces.managedbean.UserBean;
import com.eamtar.mccn.model.User;
import com.eamtar.mccn.service.ManagerService;
import com.eamtar.mccn.service.ServiceManagerFactory;
import com.eamtar.mccn.service.UserService;
import com.eamtar.mccn.servlets.ResourceInit;
import com.eamtar.mccn.util.OSDetection;

@Component(value = "fileUploadController")
@Scope("request")
public class FileUploadController extends AbstractController {

	private final String SUCCESS_MESSAGE_UPLOAD = "File Uploaded Successfully";
	private final String ERROR_MESSAGE_FILE_TYPE = "Error! Invalid File Type";
	private final String ERROR_MESSAGE_INVALID_IMAGE = "Error! Invalid Image";
	private final String ERROR_MESSAGE_INVALID_DIRECTORY = "Error! Invalid image direcotry doesnt exist.";
	private final String ERROR_MESSAGE_EXCEPTION = "An Exception has occured please see logs.";
	private final String STRING_DOT = ".";
	
	@Autowired
	private UserBean userBean;
	@Autowired
	private ManagerService managerService;

	public void handleFileUpload(FileUploadEvent event) {
		
		UploadedFile uploadedFile = event.getFile();
		String fileName = uploadedFile.getFileName();
		String imageExtension = FilenameUtils.getExtension(fileName);

		InputStream input = null;
		BufferedImage bufferedImage = null;
		String message = null;

		try {

			input = uploadedFile.getInputstream();
			bufferedImage = ImageIO.read(input);

			if (bufferedImage != null) {

				File PICTURE_DIR = new File(PICTURE_DIR_PATH);

				if (PICTURE_DIR.exists()) {

					String contentType = uploadedFile.getContentType();
					
					if (contentType.equalsIgnoreCase("image/jpg") || contentType.equalsIgnoreCase("image/jpeg")
							|| contentType.equalsIgnoreCase("image/png") || contentType.equalsIgnoreCase("image/x-png")) {

						if (userBean != null && userBean.getUserId() != null) {

							String newFileName = userBean.getUserId() + STRING_DOT + imageExtension;
							logger.info("UPLOAD DIRECTORY : "+PICTURE_DIR_PATH +" & FILE NAME : "+newFileName);
							File file = new File(PICTURE_DIR_PATH + newFileName);
							ImageIO.write(bufferedImage, imageExtension, file);

							Date currentDate = new Date();
							User user = userBean.getUser();
							user.setProfilePicture(newFileName);
							user.setModifiedDate(currentDate);

							UserService userService = managerService.getUserService();
							userService.update(user);

							message = SUCCESS_MESSAGE_UPLOAD;
						}

					} else {
						message = ERROR_MESSAGE_FILE_TYPE;
					}
				} else {
					message = ERROR_MESSAGE_INVALID_DIRECTORY+" "+PICTURE_DIR;
				}
			} else {
				message = ERROR_MESSAGE_INVALID_IMAGE;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			logger.error(exception.getMessage());
			message = ERROR_MESSAGE_EXCEPTION;
		}

		addMessageToFacesContext(message);
	}

	private static final String PICTURE_DIR_PATH = ResourceInit.getWebDirectory()+ (OSDetection.isUnix()?"/resources/images/profile/":"resources\\images\\profile\\");

}
