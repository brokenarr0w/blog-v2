package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.dao.PersonCardDao;
import com.example.backend.entity.PersonCard;
import com.example.backend.service.PersonCardService;
import org.springframework.stereotype.Service;

/**
 * (PersonCard)表服务实现类
 *
 * @author makejava
 * @since 2023-10-25 16:10:54
 */
@Service("personCardService")
public class PersonCardServiceImpl extends ServiceImpl<PersonCardDao, PersonCard> implements PersonCardService {

}

