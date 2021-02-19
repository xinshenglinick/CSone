app.controller('tbUserController' ,function($scope,$http,$controller,tbUserService){
    $controller('baseController',{$scope:$scope});//继承
    //查询所有
    $scope.findAll=function () {
        tbUserService.findAll().success(function (data) {
            $scope.tbUserlist=data;
            console.log($scope.tbUserlist);
        })
    }
    $scope.searchEntity={};
    //查询分页列表
    $scope.findPage=function (pageNum,pageSize) {
        tbUserService.findPage(pageNum,pageSize,$scope.searchEntity).success(function (data) {
            $scope.tbUserlist=data.recourds;
            $scope.paginationConf.totalItems=data.total;
        })
    }
    //回显
    $scope.getById=function (id) {
        tbUserService.getById(id).success(function (data) {
            $scope.entity=data;
        })
    }
    $scope.entity={};
    //保存
    $scope.save=function () {
        console.log($scope.entity)
        if($scope.entity.password != $scope.entity.password1){
            alert('两次密码不一致');
            return;
        }
        tbUserService.save($scope.entity).success(function (data) {
            if(data.success){
                $scope.entity={};
                alert(data.message)
                location.href="http://localhost:8088/cas/login";
            } else{
                alert(data.message);
            }

        })
    }
    //批量删除
    $scope.deletebth=function () {
        tbUserService.deletbth($scope.selecteds).success(function (data) {
            if(data.success){
                $scope.selecteds=[];
                $scope.reloadList();
            } else{
                alert(data.errmsg);
            }
        })
    }
    $scope.sendCode=function(){
        if($scope.entity.phone==null){
            alert("请输入手机号！");
            return ;
        }
        $http.get('../tbUser/sendmsg.do?phone='+$scope.entity.phone).success(function (response) {
            if(response.success){
                alert(response.message)
            }else{
                alert(response.message)
            }
        })
    }

});