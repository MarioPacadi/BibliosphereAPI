package hr.algebra.bibliosphereapi.mapper;


import hr.algebra.bibliosphereapi.dto.UserDto;
import hr.algebra.bibliosphereapi.models.Account;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    Account to(UserDto source);

    UserDto from(Account destination);

    List<UserDto> mapToDto(List<Account> packages);

    List<Account> map(List<UserDto> employees);
}
