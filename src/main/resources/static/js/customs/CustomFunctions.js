$.scope = function (scopeNames, assert) {
    var target = $(document);

    if (scopeNames !== undefined) {
        var scopeNamesArray = scopeNames.split(".");

        var length = scopeNamesArray.length;

        if (length > 1) {
            for (var i in scopeNamesArray) {
                target = target.scope(scopeNamesArray[i], assert);
            }
        } else {
            target = target.scope(scopeNames, assert)
        }
    }

    return target;
};


/**
 * 하위 엘리먼트 중 data-scope="scopeName" 을 가지고 있는 엘리먼트를 리턴한다.
 * assert 값이 false가 아니면, scope가 존재 하지 않을때 오류를 발생시킨다.
 * @param {string} scopeNames
 * @param {boolean=} assert
 * @returns {*}
 */
$.fn.scope = function (scopeNames, assert) {
    var target = $(this);

    if (scopeNames !== undefined) {
        var scopeNamesArray = scopeNames.split(".");

        var length = scopeNamesArray.length;

        if (length > 1) {
            for (var i in scopeNamesArray) {
                target = target.scope(scopeNamesArray[i], assert);
            }
        } else {
            target = target.find("[data-scope=" + scopeNames + "]");
        }
    } else {
        throw new Error("필수값이 전달되지 않았습니다. scopeNames");
    }

    return target;
};