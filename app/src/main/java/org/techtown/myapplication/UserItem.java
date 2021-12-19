package org.techtown.myapplication;

/**
 * 사용자 항목 관리
 * -사용자 항목을 관리하는 기능을 합니다.
 * @author 유세빈, 김은석, 이하나, 김동권
 */
public class UserItem
{
    private int id;
    private String nickname;
    private String password;
    private String name;
    private String sex;
    private String health;

    public UserItem()
    {
    }

    /**
     * ID 반환
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * ID 할당
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 닉네임 반환
     * @return nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 닉네임 할당
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 비밀번호 반환
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 비밀번호 할당
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 이름 반환
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 이름 할당
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 성별 반환
     * @return sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * 성별 할당
     * @param sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 건강상태 반환
     * @return health
     */
    public String getHealth() {
        return health;
    }

    /**
     * 건강상태 할당
     * @param health
     */
    public void setHealth(String health) {
        this.health = health;
    }
}
