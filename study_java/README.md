###	简介
接口使用webservice client拉取

###	URL1,获取Token
http://ebotappdata.entgroup.cn/Common/Service_Users.svc

####	登录获得Token
调用登录方法获得Token，token有效时间10分钟
ServiceUserLogin(sName,sPwd) (用户名PingTai；密码123456)

####	验证Token是否有效
该方法可以不使用，目前代码中没使用
ServiceUserTokenValid(Token)



###	URL2,具体的数据接口
http://ebotappdata.entgroup.cn/PingTai/Service_PingTai.svc

####	数据接口1，通过明星ID获取明星所有作品列表
**ProjectList_Json(sPersonID,Token)**
sPersonID为明星ID，ID为数据部ID，非本部门ID

返回字段释义:
```
ProjectID:项目ID
ProjectName:项目名称
ProjectType:项目类型ID
ProjectTypeName:项目类型名称
```

####	数据接口2，通过作品ID获取作品信息
**GetProjectDetailByID_Json(sProjeceID,Token)**
返回字段释义:
```
data1:基础信息
    ProjectID:项目ID    ProjectName:项目名称    ReleaseYear:上映年份    ReleaseDate:上映日期    PalyNum:累计网络播放量    PalyDate:播放量最新统计日期    
    DouBanGrade:豆瓣评分    GradeDate:最新评分日期    BoxOffice:票房
data2:题材
    GenreID:题材ID    GenreName:题材    Sortid:排序(10为主题材)
data3:国家
    CountryID:国家ID    CountryName:国家    Sortid:排序(10为主国家)
data4:导演
    DirectID:导演ID    DirectName:导演姓名    Sortid:排序(10为主导演)
data5:主持人（如果作品为综艺）
	PresenterID:主持人ID，主持人名字：PresenterName， Sortid:排序
data6:演员
    PerformerID:演员ID    PerformerName:演员姓名    PerformerRoleName:饰演角色    Sortid:排序(6以为上主演)
data7:嘉宾
	GuestID:嘉宾ID	GuestName:嘉宾名	Sortid:排序
```
