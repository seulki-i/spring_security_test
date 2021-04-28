$(document).ready(function () {
    Page.init();
});

const Page = {
    init : function (){
        const _this = this;

        _this.bind.button(_this);

        if($("#errorCode").val() !== ""){
            loginFailure($("#errorCode").val());
        }
    },

    bind : {
        button : function (_this){
            $("#login").on("click", function (){
                _this.methods.checkField(_this);
            });

            $("#loginForm").find("input").keypress(function (event) {
                if (event.keyCode === 13) {
                    $("#login").trigger('click');
                }
            });
        }
    },

    methods : {
        checkField : function (_this){
            if($("#userId").val() === '' || $("#userId").val() === null){
                alert("로그인아이디를 입력하세요.");
                return false;
            }else if($("#userPassword").val() === '' || $("#userPassword").val() === null){
                alert("패스워드를 입력하세요.");
                return false;
            }
            $("#loginForm").submit();
        }
    }
}

const loginFailure = function (errorCode) {
    if (errorCode === "-1") {
        swal("로그인 중 오류가 발생했습니다. \n 확인 후 다시 시도하십시오.", "", "error");
    } else if (errorCode === "-2") {
        swal("패스워드가 일치하지 않습니다. \n 확인 후 다시 시도하십시오.", "", "error");
    } else if (errorCode === "-3") {
        swal("사용할 수 없는 아이디입니다.\n 확인 후 다시 시도하십시오.", "", "error");
    } else if (errorCode === "-7") {
        swal("다른 사용자가 로그인하고 있습니다.\n 관리자에게 문의하십시오.", "", "error");
    } else if (errorCode === "-8") {
        swal("존재하지 않는 계정입니다.\n 확인 후 다시 시도하십시오.", "", "error");
    } else {
        swal("오류가 발생했습니다. 관리자에게 문의하십시오.", "", "error");
    }
};
