//创建Session时不能直接暴露entity以面密码泄露，把能暴露的单独放在dto里面
package com.max.myskills.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    private String username;
    private String password;

}
