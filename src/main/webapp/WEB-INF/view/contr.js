var app = angular.module('myApp', ['ui.bootstrap']);

app.controller('myCtrl', function ($scope, $http, $filter) {

    $scope.gap = 5;                                                 //шаг страницы для таблицы
    $scope.itemsPerPage = 5;                                        //кол-во элементов на странице
    $scope.sort = {sortingOrder: 'timez', reverse: true};           //параметры сортировки

    //массив json объектов задач
    $scope.urlst = window.location.pathname + "/mess/";
    
    //массив объектов авторов
    $scope.getAutors = function () {
        $http.get($scope.urlst + 'listAu').success(function (response) {
            $scope.itemsAu = response;
        });
    };
    
    $http.get($scope.urlst + 'list').success(function (response) {
        $scope.items = $filter('orderBy')(response, $scope.sort.sortingOrder, $scope.sort.reverse);//response;
        $scope.totalItems = $scope.items.length;                    //инициализация        
        $scope.currentPage = 1;                                     //текущая страница
        $scope.groupToPages();                                      //сгруппируем страницы для просмотра
        $scope.scrum =$scope.items[0];                              //покажем первую страницу
    });
    
    //просмотр элементов
    $scope.setItem = function (v) {
        $scope.scrum = v;
        $scope.sh_butt = false;                                     //скрыть кнопку записи
    };

    //расчет элементов страницы в таблице
    $scope.groupToPages = function () {
        $scope.pagedItems = [];                                                 //массивы станиц
        for (var i = 0; i < $scope.totalItems; i++) {
            if (i % $scope.itemsPerPage === 0) {
                $scope.pagedItems[Math.floor(i / $scope.itemsPerPage)] = [$scope.items[i]];
            } else {
                $scope.pagedItems[Math.floor(i / $scope.itemsPerPage)].push($scope.items[i]);
            }
        }
    };

    //подготовим объект для создания
    $scope.add = function () {
        $scope.getAutors();
        $scope.scrum = {sprAvtorsId: {id:"", avtor:""}, text: "", timez: new Date()};
        $scope.sh_butt = true;                                      //показать кнопку записи
    };
    
    //Сохранить сообщение
    $scope.save = function () {
        $scope.scrum.sprAvtorsId.id="-1";
        $scope.itemsAu.forEach(function(el){            
           if(el.avtor===$scope.scrum.sprAvtorsId.avtor){
               $scope.scrum.sprAvtorsId.id = el.id;
           } 
        });
        $http({
            url: $scope.urlst + 'create',
            method: "POST",
            type: "application/json",
            data: $scope.scrum         
        }).then(function (response) {  // success
            $scope.items.unshift($scope.scrum);                 //добавить в масссив сообщ. для просмотра
            $scope.totalItems = $scope.items.length;            //увеличить число для пагинатора
            $scope.currentPage = 1;                             //текущая страница
            $scope.groupToPages();                              //сгруппировать станицы
            //$log(response);
        }, function (response) { // optional  // failed
            alert("Ошибка записи!" + response);
        });
    };

});