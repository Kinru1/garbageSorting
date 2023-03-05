package com.lin.garbagesorting.vo;

import com.lin.garbagesorting.entity.Operation;
import com.lin.garbagesorting.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserVo implements Serializable {
    private User user;
    private String token;
    private List<Operation> menus;
    private List<Operation> auths;
}
