# ika_map_editor
IKA地图编辑器,IKA map Editor 是一个适用于经典物块拼接地图用的地图编辑器<br/>
集合了基本的碰撞,旋转等功能,适用于绝大多数设备,<br/>
有J2ME解析代码,android解析代码,PC(J2SE-application)解析代码,网页(applet插件)解析代码,<br/>
可直接开发普通手机游戏,智能android手机游戏,网页游戏和PC游戏,软件,<br/>
软件短小精悍,仅仅200多K但是集成了经典地图上使用的大部分功能.非常实用. <br/>
<br/>
<img src="https://github.com/airzhangfish/ika_map_editor/blob/master/doc/sample/ikamapeditor_1.jpg"/><br/>
<img src="https://github.com/airzhangfish/ika_map_editor/blob/master/doc/sample/demo_map.jpg"/><br/>

How to Use:<br/>
创建新地图:<br/>
1.打开 编辑器<br/>
2.输入地图物块大小和地图大小,点击确定<br/>
3.再屏幕上点右键,点击右键刷新左屏幕,可以看到画面上都是黑色格子<br/>
4.点击导入PNG图片,这个时候PNG图片的宽高必须为地图物块大小的整数倍,在右屏点右键刷新,可以显示出导入 
的物块.<br/>
5.进行地图编辑工作.<br/>
<br/>
打开地图:<br/>
1.打开 编辑器<br/>
2.打开...-->XXXXX.map文件<br/><br/>
3.导入PNG图片,点击右键刷新左屏幕<br/>
4.进行地图编辑工作<br/>
<br/>
导出图片+数据:<br/>
1.另存为,存储.map数据文件<br/>
2.先导出PNG小图,然后可以在指定文件夹看到所有的小图,可以进行二次编辑<br/>
3.必须进行上一个步骤之后,执行打包PNG小图,小图会被打包成数据格式,方便读取.(可根据自己的情况决定是否使用)<br/>
4.格式读取说明：<br/>
固定标识[3 byte]+层数[2 byte]+tile长宽[2 byte+2byte]+地图长宽[2 byte+2byte]+数据[2byte*地图长*地图宽]<br/>
数据2byte解析:4bit通过信息+4bit旋转信息+1byte ID图片数据.<br/>
