package cn.com.ljw.entity;

/**
 * @author Steph_Lin
 * @date 2022/11/25
 */
public class UserEntity extends BasicEntity{

    private String username;

    private String password;

    private String nickname;

    @Override
    public boolean mappingToDb() {
        return true;
    }

}
