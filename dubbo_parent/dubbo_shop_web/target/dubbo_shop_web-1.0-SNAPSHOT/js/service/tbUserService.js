app.service('tbUserService' ,function($http){

    this.findAll=function () {
        return $http.get("../tbUser/findAll.do");
    }

    this.findPage=function (pageNum,pageSize,searchEntity) {
        return $http.post("../tbUser/pageList.do?pageSize="+pageSize+"&pageNum="+pageNum,searchEntity);
    }

    this.getById=function (id){
        return $http.get("../tbUser/getById.do?id="+id);
    }
    
    this.save =function (entity) {
        return $http.post("../tbUser/save.do",entity);
    }
    this.deletbth = function (selecteds) {
        return $http.get("../tbUser/deletebth.do?ids="+selecteds);
    }
    this.sendCode=function(phone){
        return  $http.get('../tbUser/sendmsg.do?phone='+phone);
    }
})