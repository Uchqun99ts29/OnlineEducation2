package com.education.service.auth;

import com.education.config.encriptions.PasswordEncoderConfigurer;
import com.education.criteria.AuthUserCriteria;
import com.education.dto.auth.*;
import com.education.dto.response.AppErrorDto;
import com.education.dto.response.DataDto;
import com.education.entity.Address;
import com.education.entity.AuthUser;
import com.education.entity.Education;
import com.education.mapper.AddressMapper;
import com.education.mapper.AuthUserMapper;
import com.education.properties.ServerProperties;
import com.education.repository.AddressRepository;
import com.education.repository.AuthUserRepository;
import com.education.repository.EducationRepository;
import com.education.service.AbstractService;
import com.education.service.FileStorageService;
import com.education.service.address.AddressServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;


@Service
public class AuthUserServiceImpl extends AbstractService<AuthUserRepository, AuthUserMapper> implements UserDetailsService, AuthUserService {

    private final ServerProperties serverProperties;
    private final ObjectMapper objectMapper;
    private final FileStorageService fileStorageService;
    private final AddressMapper addressMapper;
    private final EducationRepository educationRepository;
    private final AddressRepository addressRepository;
    private final PasswordEncoderConfigurer passwordEncoderConfigurer;

    protected AuthUserServiceImpl(AuthUserRepository repository, AuthUserMapper mapper, ServerProperties serverProperties, ObjectMapper objectMapper, FileStorageService fileStorageService, AddressServiceImpl addressService, AddressMapper addressMapper, EducationRepository educationRepository, AddressRepository addressRepository, PasswordEncoderConfigurer passwordEncoderConfigurer) {
        super(repository, mapper);
        this.serverProperties = serverProperties;
        this.objectMapper = objectMapper;
        this.fileStorageService = fileStorageService;
        this.addressMapper = addressMapper;
        this.educationRepository = educationRepository;
        this.addressRepository = addressRepository;
        this.passwordEncoderConfigurer = passwordEncoderConfigurer;
    }


    @Override
    public ResponseEntity<DataDto<Long>> create(AuthUserCreateDto createDto) {

        Education education = educationRepository.getNotDelete(createDto.getEducationId());


        AuthUser authUser = mapper.fromCreateDto(createDto);
        authUser.setEducation(education);

        Address address = addressRepository.save(authUser.getAddress());
        authUser.setAddress(address);
        AuthUser user = repository.save(authUser);
        return new ResponseEntity<>(new DataDto<>(user.getId()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<Void>> delete(Long id) {

        AuthUser authUser = repository.getNotDelete(id, 1L);

        if (Objects.isNull(authUser)) throw new NotFoundException("user not found");

        authUser.setDeleted(true);

        repository.save(authUser);

        return new ResponseEntity<>(new DataDto<>(null), HttpStatus.OK);
    }




    @Override
    public ResponseEntity<DataDto<Long>> update(AuthUserUpdateDto updateDto) {

        AuthUser authUser = mapper.fromUpdateDto(updateDto);

        return new ResponseEntity<>(new DataDto<>(repository.save(authUser).getId()), HttpStatus.OK);
    }




    @Override
    public ResponseEntity<DataDto<List<AuthUserDto>>> getAll(AuthUserCriteria criteria) {
        List<AuthUser> authUsers = repository.getAllNotDelete(criteria.getPage(), criteria.getSize(), 1L);
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(authUsers)), HttpStatus.OK);


    }


    public ResponseEntity<DataDto<Void>> block(Long id) {
        return block(id,1L);
    }



    public ResponseEntity<DataDto<Void>> block(Long id, Long educationId) {

        AuthUser authUser = repository.getNotDelete(id, educationId);
        if (Objects.isNull(authUser)) throw new NotFoundException("user not found");
        authUser.setBlock(!authUser.isBlock());
        repository.save(authUser);
        return new ResponseEntity<>(new DataDto<>(null), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<DataDto<AuthUserDto>> get(Long id) {

        AuthUser authUser = repository.getNotDelete(id, 1L);

        if (Objects.isNull(authUser)) throw new NotFoundException("User not found");

        return new ResponseEntity<>(new DataDto<>(mapper.toDto(authUser)), HttpStatus.OK);


    }

    @Override
    public ResponseEntity<DataDto<Long>> totalCount(AuthUserCriteria criteria) {
        return null;
    }

    public ResponseEntity<DataDto<SessionDto>> getToken(LoginDto dto) {
        try {
            HttpClient httpclient = HttpClientBuilder.create().build();
            HttpPost httppost = new HttpPost(serverProperties.getServerUrl() + "/api/login");
            byte[] bytes = objectMapper.writeValueAsBytes(dto);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            httppost.addHeader("Content-Type", "application/x-www-form-urlencoded");
            httppost.setEntity(new InputStreamEntity(byteArrayInputStream));

            HttpResponse response = httpclient.execute(httppost);

            JsonNode json_auth = objectMapper.readTree(EntityUtils.toString(response.getEntity()));

            if (json_auth.has("success") && json_auth.get("success").asBoolean()) {
                JsonNode node = json_auth.get("data");
                SessionDto sessionDto = objectMapper.readValue(node.toString(), SessionDto.class);
                return new ResponseEntity<>(new DataDto<>(sessionDto), HttpStatus.OK);
            }
            return new ResponseEntity<>(new DataDto<>(objectMapper.readValue(json_auth.get("error").toString(), AppErrorDto.class)), HttpStatus.OK);

        } catch (IOException e) {
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().message(e.getLocalizedMessage()).status(HttpStatus.INTERNAL_SERVER_ERROR).build()), HttpStatus.OK);
        }
    }

    public ResponseEntity<DataDto<SessionDto>> refreshToken(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser user = (AuthUser) repository.findByUsernameAndDeletedFalse(username).orElseThrow(() -> {
            throw new UsernameNotFoundException("User not found");
        });
        return User.builder().username(user.getUsername()).password(user.getPassword()).authorities(user.getAuthority()).accountLocked(false).accountExpired(false).disabled(false).credentialsExpired(false).build();
    }
}
