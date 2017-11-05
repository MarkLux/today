package com.marklux.dto.response;

/**
 * Created by mark on 17/11/1.
 */
public class UserLoginResponse {

    private String token;
    private User user;

    public UserLoginResponse(String token,com.marklux.domain.User user) {
        this.token = token;
        User u = new User();
        u.setName(user.getName());
        u.setCreatedAt(user.getCreatedAt());
        u.setUpdatedAt(user.getUpdatedAt());
        u.setSex(user.getSex());
        u.setSignature(user.getSignature());
        this.user = u;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    class User {
        private String name;
        private String signature;
        private int sex;
        private long createdAt;
        private long updatedAt;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public long getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(long createdAt) {
            this.createdAt = createdAt;
        }

        public long getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(long updatedAt) {
            this.updatedAt = updatedAt;
        }
    }
}
