package com.kits.project.util;

import com.kits.project.DTOs.AccountCreateDTO;
import com.kits.project.model.Account;
import org.modelmapper.ModelMapper;

public class ConvertDTOToModel {
    public ConvertDTOToModel(){}

    static ModelMapper mapper = new ModelMapper();

    public static Account convertAccountCreateDTOToUser(AccountCreateDTO accountCreateDTO)
    {
        return mapper.map(accountCreateDTO, Account.class);
    }

}
