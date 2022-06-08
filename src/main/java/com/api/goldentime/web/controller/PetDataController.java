package com.api.goldentime.web.controller;

import com.api.goldentime.domain.crawling.PetData;
import com.api.goldentime.service.post.PetDataService;
import com.api.goldentime.web.dto.response.ResponseDto;
import com.api.goldentime.web.dto.response.crawling.SimilarityResponseDto.SimilarityResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Api("Pet Data Controller")
public class PetDataController {

    private final PetDataService petDataService;

    @ApiOperation(value = "크롤링 데이터 저장 ")
    @PostMapping("/excel/read")
    public void readExcel(@RequestPart(value="file",required = false) MultipartFile file)
            throws IOException {

        System.out.println(file.getName());
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

            String post_num = row.getCell(1).getStringCellValue();
            String[] region = post_num.split("-");
            utility(region);
            data.setRegion_1depth_name(region[0]);
            data.setRegion_2depth_name(region[1]);
            data.setRegion_3depth_name("");

            data.setReportDate(row.getCell(2).getDateCellValue().toString());
            data.setKind(row.getCell(3).getStringCellValue());
            data.setGender(row.getCell(4).getStringCellValue());
            data.setLostPlace(row.getCell(5).getStringCellValue());
            data.setRemark(row.getCell(6).getStringCellValue());
            data.setDetailLink(row.getCell(7).getStringCellValue());

            //db에 저장
            petDataService.save(data);

            //id를 엑셀에 삽입
        }
    }

    @GetMapping("/pet/data/{id}")
    public ResponseEntity<?> getPetDataById(@PathVariable Long id) {
        PetData petData = petDataService.findById(id);
        SimilarityResponse petDataResponse = SimilarityResponse.of(petData);

        ResponseDto<SimilarityResponse> response = ResponseDto.<SimilarityResponse>builder()
            .status(ResponseDto.ResponseStatus.SUCCESS)
            .message("분실 신고 게시물 목록 조회 성공")
            .data(petDataResponse)
            .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    void utility(String[] region)
    {
        switch(region[0]) {
                case "경북":
                    region[0] = "경상북도";
                    if(!region[1].contains("시")) {
                        region[1] += "시";
                    }
                    break;
                case "경남":
                    region[0] = "경상남도";
                    if(!region[1].contains("시")) {
                        region[1] += "시";
                    }
                    break;
                case "경기":
                    region[0] = "경기도";
                    if(!region[1].contains("시")) {
                        region[1] += "시";
                    }
                    break;
                case "전남":
                    region[0] = "전라남도";
                    if(!region[1].contains("시")) {
                        region[1] += "시";
                    }
                    break;
                case "충남":
                    region[0] = "충청남도";
                    if(!region[1].contains("시")) {
                        region[1] += "시";
                    }
                    break;
                case "대구":
                    region[0] = "대구시";
                    if(!region[1].contains("구")) {
                        region[1] += "구";
                    }
                    break;
                case "부산":
                    region[0] = "부산광역시";
                    if(!region[1].contains("구")) {
                        region[1] += "구";
                    }
                    break;
                case "인천":
                    region[0] = "인천광역시";
                    if(!region[1].contains("구")) {
                        region[1] += "구";
                    }
                    break;
                case "제주":
                    region[0] = "제주특별자치도";
                    if(!region[1].contains("시")) {
                        region[1] += "시";
                    }
                    break;
                case "강원":
                    region[0] = "강원도";
                    if(!region[1].contains("시")) {
                        region[1] += "시";
                    }
                    break;
                case "울산":
                    region[0] = "울산광역시";
                    if(!region[1].contains("군")) {
                        region[1] += "군";
                    }
                    break;
                case "대전":
                    region[0] = "대전광역시";
                    if(!region[1].contains("구")) {
                        region[1] += "구";
                    }
                    break;
                case "전북":
                    region[0] = "전라북도";
                    if(!region[1].contains("시")) {
                        region[1] += "시";
                    }
                    break;
                case "충북":
                    region[0] = "충청북도";
                    if(!region[1].contains("시")) {
                        region[1] += "시";
                    }
                    break;
                case "서울":
                    region[0] = "서울특별시";
                    if(!region[1].contains("구")) {
                        region[1] += "구";
                    }
                    break;
                case "광주":
                    region[0] = "광주광역시";
                    if(!region[1].contains("구")) {
                        region[1] += "구";
                    }
                    break;
            }

    }

}

