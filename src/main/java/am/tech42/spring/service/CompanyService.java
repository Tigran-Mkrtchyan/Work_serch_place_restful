package am.tech42.spring.service;

import am.tech42.spring.dto.CompanyDto;
import am.tech42.spring.dto.ReturnUserDto;
import am.tech42.spring.model.Company;
import am.tech42.spring.model.Logo;
import am.tech42.spring.model.User;
import am.tech42.spring.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@Service
@Transactional
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private UserService userService;


    public ReturnUserDto saveCompany(CompanyDto companyDto){
        User user = userService.createUser(companyDto);
        Company company =new Company();
        company.setUser(user);
        company.setCompanyName(companyDto.getCompanyName());
        company.setContactNumber(companyDto.getPhoneNumber());
        companyRepository.save(company);
        ReturnUserDto newUser = new ReturnUserDto();
        newUser.setUserId(user.getId());
        newUser.setName(companyDto.getCompanyName());
        newUser.setRole(user.getRole());
        return newUser;
    }
    public String saveLogo(String id, MultipartFile logoFile) {
        Company company =  companyRepository.findCompanyByUserId(id).get();
        String fileName = company.getCompanyName()+".jpg";
        String logoPath = "C:\\Users\\Galust\\Desktop\\uploads\\logos\\"+fileName;
        File cvFile = new File(logoPath);
        try {
            logoFile.transferTo(cvFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Logo newLogo = new Logo();
        newLogo.setLogoUrl(logoPath);
        company.addLogo(newLogo);
        companyRepository.save(company);

        return logoPath;
    }
}
