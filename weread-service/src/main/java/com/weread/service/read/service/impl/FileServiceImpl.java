package com.weread.service.read.service.impl;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.java2nb.novel.core.utils.Constants;
import com.java2nb.novel.core.utils.FileUtil;
import com.weread.service.read.service.FileService;

@Service
@ConditionalOnProperty(prefix = "pic.save", name = "storage", havingValue = "local")
public class FileServiceImpl implements FileService {

	@Override
	public String transFile(String picSrc, String picSavePath) {
		return FileUtil.network2Local(picSrc, picSavePath, Constants.LOCAL_PIC_PREFIX);
	}

}
