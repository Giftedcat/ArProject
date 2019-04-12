package com.eastudio.ardemo.http.model;

import java.io.Serializable;

/**
 *
 * @author Administrator
 * @date 2017/10/19/019
 */

public class UserInfo implements Serializable{

    /**
     * student : {"balance":33.52,"balanceUpdateTime":1517306415000,"birth":"1999-3-5","classNumber":701,"created":1517306444000,"deviceNumber":2,"gender":true,"grade":"701","headImg":"http://img3.imgtn.bdimg.com/it/u=1121475478,2545730346&fm=214&gp=0.jpg","height":122.2,"id":2,"modified":1517306446000,"schoolName":"下沙中学","schoolNumber":5113849,"studentCompositeNumber":2,"studentName":"测试学生2","studentNumber":2,"watchSim":"1","weight":152.2}
     * user : {"headImg":"http://img4.imgtn.bdimg.com/it/u=1611505379,380489200&fm=214&gp=0.jpg","id":723,"modified":1517385279000,"nickname":"小徐","parentNumber":3,"phone":"13732155246","token":"qLJvW1VrtSasHPZFyjgNGw==","tokenExpireTime":1517558079596,"username":"13732155246"}
     */

    private String verifyCode;
    private String username;
    private boolean isBind;
    private String content,imageString,latitude,longitude,address,clientType,dynamicNumber,isPraise;
    /**
     * qiniuyunToken : QyIM3XNABvCBYBbkEWv6Y7yu7cjJYrAeWV_uOPrv:k8ImEfQTZnI-W47UWwkaPFo63Ms=:eyJzY29wZSI6Imtsbi1zY2hvb2wtYnVja2V0IiwiZGVhZGxpbmUiOjE1MTg0MDg1NTJ9
     * student : {"birth":"1999-3-5","classNumber":3100180001701,"deviceNumber":2,"gender":true,"grade":"701","headImg":"http://p3nxih6mc.bkt.clouddn.com/20180211120513606716146HeadImg","height":122.2,"schoolName":"景苑中学","schoolNumber":3100180001,"studentCompositeNumber":3100180001011111112,"studentName":"戴高乐","studentNumber":"20171036","watchSim":"15267533575","weight":152.2}
     * user : {"created":1517305064000,"headImg":"http://p3nxih6mc.bkt.clouddn.com/20180211111513732155246HeadImg","id":723,"latestJpushDeviceType":"android","latestJpushToken":"170976fa8a8a0c31d6b","modified":1518404318000,"nickname":"郑涛","parentNumber":3,"phone":"13732155246","token":"8L40Gp0EIKsPQMw3UqVPuw==","tokenExpireTime":1518577752152,"username":"13732155246"}
     */

    private String qiniuyunToken;
    private StudentBean student;
    private UserBean user;


    public boolean isBind() {
        return isBind;
    }

    public void setBind(boolean bind) {
        isBind = bind;
    }

    public String getDynamicNumber() {
        return dynamicNumber;
    }

    public void setDynamicNumber(String dynamicNumber) {
        this.dynamicNumber = dynamicNumber;
    }

    public String getIsPraise() {
        return isPraise;
    }

    public void setIsPraise(String isPraise) {
        this.isPraise = isPraise;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageString() {
        return imageString;
    }

    public void setImageString(String imageString) {
        this.imageString = imageString;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getQiniuyunToken() {
        return qiniuyunToken;
    }

    public void setQiniuyunToken(String qiniuyunToken) {
        this.qiniuyunToken = qiniuyunToken;
    }

    public StudentBean getStudent() {
        if (student == null){
            student = new StudentBean();
        }
        return student;
    }

    public void setStudent(StudentBean student) {
        this.student = student;
    }

    public UserBean getUser() {
        if (user == null){
            user = new UserBean();
        }
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class StudentBean {
        /**
         * birth : 1999-3-5
         * classNumber : 3100180001701
         * deviceNumber : 2
         * gender : true
         * grade : 701
         * headImg : http://p3nxih6mc.bkt.clouddn.com/20180211120513606716146HeadImg
         * height : 122.2
         * schoolName : 景苑中学
         * schoolNumber : 3100180001
         * studentCompositeNumber : 3100180001011111112
         * studentName : 戴高乐
         * studentNumber : 20171036
         * watchSim : 15267533575
         * weight : 152.2
         */

        private long age;
        private String birth;
        private long classNumber;
        private long deviceNumber;
        private boolean gender;
        private String grade;
        private String headImg;
        private double height;
        private String schoolName;
        private long schoolNumber;
        private long studentCompositeNumber;
        private String studentName;
        private String studentNumber;
        private String watchSim;
        private double weight;

        public long getAge() {
            return age;
        }

        public void setAge(long age) {
            this.age = age;
        }

        public String getBirth() {
            return birth;
        }

        public void setBirth(String birth) {
            this.birth = birth;
        }

        public long getClassNumber() {
            return classNumber;
        }

        public void setClassNumber(long classNumber) {
            this.classNumber = classNumber;
        }

        public long getDeviceNumber() {
            return deviceNumber;
        }

        public void setDeviceNumber(long deviceNumber) {
            this.deviceNumber = deviceNumber;
        }

        public boolean isGender() {
            return gender;
        }

        public void setGender(boolean gender) {
            this.gender = gender;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public double getHeight() {
            return height;
        }

        public void setHeight(double height) {
            this.height = height;
        }

        public String getSchoolName() {
            return schoolName;
        }

        public void setSchoolName(String schoolName) {
            this.schoolName = schoolName;
        }

        public long getSchoolNumber() {
            return schoolNumber;
        }

        public void setSchoolNumber(long schoolNumber) {
            this.schoolNumber = schoolNumber;
        }

        public long getStudentCompositeNumber() {
            return studentCompositeNumber;
        }

        public void setStudentCompositeNumber(long studentCompositeNumber) {
            this.studentCompositeNumber = studentCompositeNumber;
        }

        public String getStudentName() {
            return studentName;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
        }

        public String getStudentNumber() {
            return studentNumber;
        }

        public void setStudentNumber(String studentNumber) {
            this.studentNumber = studentNumber;
        }

        public String getWatchSim() {
            return watchSim;
        }

        public void setWatchSim(String watchSim) {
            this.watchSim = watchSim;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }
    }

    public static class UserBean {
        /**
         * created : 1517305064000
         * headImg : http://p3nxih6mc.bkt.clouddn.com/20180211111513732155246HeadImg
         * id : 723
         * latestJpushDeviceType : android
         * latestJpushToken : 170976fa8a8a0c31d6b
         * modified : 1518404318000
         * nickname : 郑涛
         * parentNumber : 3
         * phone : 13732155246
         * token : 8L40Gp0EIKsPQMw3UqVPuw==
         * tokenExpireTime : 1518577752152
         * username : 13732155246
         */

        private long created;
        private String headImg;
        private long id;
        private String latestJpushDeviceType;
        private String latestJpushToken;
        private long modified;
        private String nickname;
        private long parentNumber;
        private String phone;
        private String token;
        private long tokenExpireTime;
        private String username;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public long getCreated() {
            return created;
        }

        public void setCreated(long created) {
            this.created = created;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getLatestJpushDeviceType() {
            return latestJpushDeviceType;
        }

        public void setLatestJpushDeviceType(String latestJpushDeviceType) {
            this.latestJpushDeviceType = latestJpushDeviceType;
        }

        public String getLatestJpushToken() {
            return latestJpushToken;
        }

        public void setLatestJpushToken(String latestJpushToken) {
            this.latestJpushToken = latestJpushToken;
        }

        public long getModified() {
            return modified;
        }

        public void setModified(long modified) {
            this.modified = modified;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public long getParentNumber() {
            return parentNumber;
        }

        public void setParentNumber(long parentNumber) {
            this.parentNumber = parentNumber;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public long getTokenExpireTime() {
            return tokenExpireTime;
        }

        public void setTokenExpireTime(long tokenExpireTime) {
            this.tokenExpireTime = tokenExpireTime;
        }

    }
}
