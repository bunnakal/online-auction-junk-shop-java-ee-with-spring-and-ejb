/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function buy(postId) {
    $.ajax({
        url: '/OnlineJunkShop/buy/' + postId,
        method: 'GET',
        success: function (data) {
            alert("Successfull perform action!!!");
        }
    });
    alert("Successfull send this product to buy status!!!");
}
function wish(postId) {
    $.ajax({
        url: '/OnlineJunkShop/wish/' + postId,
        method: 'GET',
        success: function (data) {
            alert("Successfull perform action!!!");
        }
    });
    alert("Successfull send this product to wished list!!!");
}

function addComment(postId) {
    var comment = document.getElementById("commentText" + postId).value;
    $.ajax({
        url: '/OnlineJunkShop/addComment/postId/' + postId + "/comment/" + comment,
        method: 'GET',
        success: function (data) {
            alert("Successfull perform action!!!");
        }
    });
    var text = document.getElementById('comment');
    document.getElementById('addComment').onclick = function (e) {
        e.preventDefault();
        text.innerHTML += "<div class='commontText<p>" + comment + "</p></div>";
    };
    alert("Successfull add a comment on this post!!!");
    
}

