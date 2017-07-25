<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/3
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<s:set var="basePath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="utf-8"/>
    <title>BIOGRAPHY</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <link rel="stylesheet" href="${basePath}/static/common/css/bio.css">
</head>
<body>
<section class="content">
    <div style="display: inline-block; float: left; padding-top: 25px">
        <ul class="nav nav-secondary nav-stacked" style="width: 200px; text-align: center">
            <li class="active"><a data-tab href="#Personal">JACKY</a></li>
            <li><a data-tab href="#Films">影视</a></li>
            <li><a data-tab href="#Variety">综艺</a></li>
            <li><a href="">奖项</a></li>
            <li><a href="">社会</a></li>
        </ul>
    </div>
    <div class="container-fluid" style="display: inline-block; width: 80%">
        <article class="article article-condensed">
            <header>
                <h1 class="text">薛之谦（Jacky）&nbsp;&nbsp;&nbsp;&nbsp;
                    <small>1983年7月17日（农历6月8日）| 上海 | 中国</small>
                </h1>
                <dl class="dl-inline pull-right">
                    <dt>部分来源：</dt>
                    <dd>百度百科</dd>
                </dl>
                <section class="abstract">
                    <p style="padding-left: 10px">“我宁愿留在你方圆几里，我的心要不回就送你。”</p>
                </section>
                <br>
            </header>
            <img class="img-rounded"
                 style="display: inline-block; width: 25%; float: left; padding-left: 10px; margin-top: 20px"
                 src="../../static/common/images/bio.png">
            <section class="content tab-content" style="display: inline-block; width: 75%; left: 300px">
                <div class="tab-pane fade active in" id="Personal">
                    <blockquote>
                        <strong>
                            他曾在纺织厂当学徒，当过淘宝店店主，当过火锅店老板，只为用心做音乐；
                            <br>十年前，一首《认真的雪》唱遍大江南北；十年后，却因微博段子再度走红。
                        </strong>
                        <hr>
                        薛之谦，华语流行乐男歌手、影视演员、音乐制作人，毕业于格里昂酒店管理学院。
                        <hr>
                        2005年，因参加选秀节目《我型我秀》而正式出道；
                        <br>2006年，发行首张同名专辑《薛之谦》，随后凭借歌曲《认真的雪》获得广泛关注；
                        <br>2007年，凭借专辑《你过得好吗》获得MusicRadio中国TOP排行榜内地年度最受欢迎男歌手奖；
                        <br>2008年，发行专辑《深深爱过你》，同年在上海举行个人首场演唱会“谦年传说”；
                        <br>2013年，专辑《几个薛之谦》获得MusicRadio中国TOP排行榜内地推荐唱片；
                        <br>2014年，凭借专辑《意外》获得第21届东方风云榜颁奖最佳唱作人，同年他还获得音悦V榜年度盛典年度创作歌手奖。
                        <br>2015年6月，薛之谦首度担当制作人并发行原创EP《绅士》，随后签约海蝶音乐，同年，他还主演都市励志剧《妈妈像花儿一样》；
                        <br>2016年3月，凭借EP《绅士》、《一半》获得第十六届全球华语歌曲排行榜最受欢迎男歌手、五大最受欢迎男歌手奖、上海地区杰出歌手奖及最受欢迎创作歌手奖，而歌曲《初学者》则获得年度二十大金曲奖。
                    </blockquote>
                </div>
                <div class="tab-pane fade" id="Films">
                    <blockquote>
                        <label class="label">电视作品：</label>

                    </blockquote>
                </div>
                <div class="tab-pane fade" id="Variety">
                    <blockquote>
                        <div class="list scrollbar-hover" style="overflow-y: scroll">
                            <section class="items">
                                <a href="#">
                                    <div class="item">《我们的挑战》（江苏卫视）</div>
                                </a>
                                <a data-toggle="collapse" href="#huoxing2">
                                    <div class="item">《火星情报局2》（网綜）</div>
                                </a>
                                <div class="collapse" id="huoxing2">
                                    <table class="table">
                                        <tr>
                                            <td>2017-01-20</td>
                                            <td>2017-01-13</td>
                                            <td>2016-12-02</td>
                                            <td>2016-11-18</td>
                                        </tr>
                                        <tr>
                                            <td>2016-11-11</td>
                                            <td>2016-11-04</td>
                                        </tr>
                                    </table>
                                </div>
                                <a data-toggle="collapse" href="#gesheng">
                                    <div class="item">《中国新歌声》（浙江卫视）</div>
                                </a>
                                <div class="collapse" id="gesheng">
                                    <table class="table table-borderless">
                                        <tr>
                                            <td>2016-10-07</td>
                                            <td>2016-09-15</td>
                                        </tr>
                                    </table>
                                </div>
                                <a href="#">
                                    <div class="item">《加油！向未来》（央视）</div>
                                </a>
                                <a href="#">
                                    <div class="item">《跨界歌王》（湖南卫视）</div>
                                </a>
                                <a href="#">
                                    <div class="item">《挑战者联盟2》（浙江卫视）</div>
                                </a>
                                <a data-toggle="collapse" href="#xinshengdai">
                                    <div class="item">《中国新声代》（湖南金鹰卡通）</div>
                                </a>
                                <div class="collapse" id="xinshengdai">
                                    <table class="table table-borderless">
                                        <tr>
                                            <td>2016-08-13</td>
                                            <td>2016-08-06</td>
                                            <td>2016-07-30</td>
                                            <td>2016-07-23</td>
                                        </tr>
                                        <tr>
                                            <td>2016-07-16</td>
                                            <td>2016-07-09</td>
                                            <td>2016-07-02</td>
                                        </tr>
                                    </table>
                                </div>
                                <a href="#">
                                    <div class="item">《大学生来了》（网綜）</div>
                                </a>
                                <a href="#">
                                    <div class="item">《我想和你唱》（湖南卫视）</div>
                                </a>
                                <a href="#">
                                    <div class="item">《作战吧偶像》（网綜）</div>
                                </a>
                                <a data-toggle="collapse" href="#huoxing">
                                    <div class="item">《火星情报局》（网綜）</div>
                                </a>
                                <div class="collapse" id="huoxing">
                                    <table class="table table-borderless">
                                        <tr>
                                            <td>2016-06-24</td>
                                            <td>2016-06-17</td>
                                            <td>2016-06-10</td>
                                            <td>2016-05-06</td>
                                        </tr>
                                        <tr>
                                            <td>2016-04-29</td>
                                            <td>2016-04-22</td>
                                            <td>2016-04-08</td>
                                        </tr>
                                    </table>
                                </div>
                                <a data-toggle="collapse" href="#bingxiang">
                                    <div class="item">《拜托了冰箱2》（网綜）</div>
                                </a>
                                <div class="collapse" id="bingxiang">
                                    <table class="table table-borderless">
                                        <tr>
                                            <td>2016-06-15</td>
                                            <td>2016-06-08</td>
                                        </tr>
                                    </table>
                                </div>
                                <a href="#">
                                    <div class="item">《端午金曲捞》（江苏卫视）</div>
                                </a>
                                <a data-toggle="collapse" href="#mishi">
                                    <div class="item">《开心密室》（网綜）</div>
                                </a>
                                <div class="collapse" id="mishi">
                                    <table class="table table-borderless">
                                        <tr>
                                            <td>2016-06-08</td>
                                            <td>2016-05-18</td>
                                        </tr>
                                    </table>
                                </div>
                                <a href="#">
                                    <div class="item">《六一惊奇夜》（湖南金鹰卡通）</div>
                                </a>
                                <a href="#">
                                    <div class="item">《你正常吗3》（网綜）</div>
                                </a>
                                <a href="#">
                                    <div class="item">《透鲜滴星期天》（网綜）</div>
                                </a>
                                <a href="#">
                                    <div class="item">《全员加速中2》（湖南卫视）</div>
                                </a>
                                <a data-toggle="collapse" href="#kanjian">
                                    <div class="item">《看见你的声音》（江苏卫视）</div>
                                </a>
                                <div class="collapse" id="kanjian">
                                    <table class="table table-borderless">
                                        <tr>
                                            <td>2016-05-22</td>
                                            <td>2016-05-15</td>
                                            <td>2016-05-08</td>
                                            <td>2016-04-24</td>
                                        </tr>
                                        <tr>
                                            <td>2016-03-27</td>
                                        </tr>
                                    </table>
                                </div>
                                <a href="#">
                                    <div class="item">《燃烧吧！卡路里》（东方卫视）</div>
                                </a>
                                <a href="#">
                                    <div class="item">《香蕉打卡》（网綜）</div>
                                </a>
                                <a href="#">
                                    <div class="item">《年代秀》（深圳卫视）</div>
                                </a>
                                <a href="#">
                                    <div class="item">《极限挑战2》（东方卫视）</div>
                                </a>
                                <a data-toggle="collapse" href="#geshen">
                                    <div class="item">《谁是大歌神》（浙江卫视）</div>
                                </a>
                                <div class="collapse" id="geshen">
                                    <table class="table table-borderless">
                                        <tr>
                                            <td>2016-04-24</td>
                                            <td>2016-04-03</td>
                                            <td>2016-03-27</td>
                                            <td>2016-03-20</td>
                                        </tr>
                                        <tr>
                                            <td>2016-03-13</td>
                                            <td>2016-03-06</td>
                                        </tr>
                                    </table>
                                </div>
                                <a data-toggle="collapse" href="#shouying">
                                    <div class="item">《超级大首映》（安徽卫视）</div>
                                </a>
                                <div class="collapse" id="shouying">
                                    <table class="table table-borderless">
                                        <tr>
                                            <td>2016-04-17</td>
                                            <td>2016-04-03</td>
                                            <td>2016-03-27</td>
                                        </tr>
                                    </table>
                                </div>
                                <a href="#">
                                    <div class="item">《大牌驾到2》（网綜）</div>
                                </a>
                                <a href="#">
                                    <div class="item">《静距离》（网綜）</div>
                                </a>
                                <a data-toggle="collapse" href="#yanzhi">
                                    <div class="item">《颜值大战》（网綜）</div>
                                </a>
                                <div class="collapse" id="yanzhi">
                                    <table class="table table-borderless">
                                        <tr>
                                            <td>2016-04-06</td>
                                            <td>2016-03-23</td>
                                        </tr>
                                    </table>
                                </div>
                                <a href="#">
                                    <div class="item">《天天向上》（湖南卫视）</div>
                                </a>
                                <a href="#">
                                    <div class="item">《偶滴歌神啊》（深圳卫视）</div>
                                </a>
                            </section>
                        </div>
                    </blockquote>
                </div>
            </section>
        </article>
    </div>
</section>
<script src="${basePath}/static/ZUI1.5/lib/jquery/jquery.js"></script>
<script src="${basePath}/static/common/js/bio.js"></script>
</body>
</html>
