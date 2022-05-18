package com.api.goldentime.web.controller;

import com.api.goldentime.domain.crawling.PetData;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ExcelController {

    @PostMapping("/excel/read")
    public void readExcel(@RequestParam("file") MultipartFile file)
            throws IOException {

        //List<PetData> dataList = new ArrayList<>();

        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            throw new IOException("엑셀파일만 업로드 해주세요.");
        }

        Workbook workbook = null;

        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        Sheet worksheet = workbook.getSheetAt(0);

        //한 row씩 read해서 db에 저장
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {

            Row row = worksheet.getRow(i);
            PetData data = new PetData();

            data.setImgUrl(row.getCell(0).getStringCellValue());
            data.setPostNum(row.getCell(1).getStringCellValue());
            data.setReportDate(row.getCell(2).getStringCellValue());
            data.setKind(row.getCell(3).getStringCellValue());
            data.setGender(row.getCell(4).getStringCellValue());
            data.setLostPlace(row.getCell(5).getStringCellValue());
            data.setDetailLink(row.getCell(6).getStringCellValue());

            //db에 저장

            //id를 엑셀에 삽입
        }


    }



}
