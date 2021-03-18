//■数値から円グラフを生成
	function drawGraph() {

	let atai = [];
	//span要素の中身（金額）を取得
	atai[0] = document.getElementById('food').innerHTML;
	atai[1] = document.getElementById('commodity').innerHTML;
	atai[2] = document.getElementById('amusement').innerHTML;
	atai[3] = document.getElementById('special').innerHTML;
	atai[4] = document.getElementById('fixed').innerHTML;
	atai[5] = document.getElementById('other').innerHTML;

	// 文字列を数値に変換
	atai[0] = parseInt(atai[0]);
	atai[1] = parseInt(atai[1]);
	atai[2] = parseInt(atai[2]);
	atai[3] = parseInt(atai[3]);
	atai[4] = parseInt(atai[4]);
	atai[5] = parseInt(atai[5]);

	console.table(atai);

	//多次元配列 multipleArray に、カテゴリ名・金額・色をセット
   let multipleArray = [
       ['食費', atai[0], 'red'],
       ['娯楽費', atai[1], 'blue'],
       ['日用品費', atai[2], 'green'],
       ['固定費', atai[3], 'yellow'],
       ['特別費', atai[4], 'orange'],
       ['その他', atai[5], 'black']
    ];

    console.table(multipleArray);

	//金額の降順にソート
    multipleArray.sort(function(a,b) {
        return b[1] - a[1];
    });

	//金額のみ取得、合計金額を求める
    var A = multipleArray[0][1];
    var B = multipleArray[1][1];
    var C = multipleArray[2][1];
    var D = multipleArray[3][1];
    var E = multipleArray[4][1];
    var F = multipleArray[5][1];
    var sum = A + B + C + D + E + F;

	//比率に応じて角度を算出
	var angleA = 360 * (A / sum);
	var angleB = 360 * (B / sum);
	var angleC = 360 * (C / sum);
	var angleD = 360 * (D / sum);
	var angleE = 360 * (E / sum);
	var angleF = 360 * (F / sum);

	var canvas = document.getElementById('target');
	// 描画コンテキストの取得（平面図形を描く）
	var context = canvas.getContext('2d');

	// 描画開始の宣言
	context.beginPath();
	// context.arc(x,y,r,sAngle,eAngle,clockwise);
	// sAngle…描画を開始する円の角度、eAngle…描画を終了する円の角度、clockwise…時計回りに描画するかどうか
	// Math.PI…円周率、開始位置は真上（0°）なので-90°する、ラジアンに変換（× Math.PI / 180）
	context.arc(100, 100, 100, (0-90) * Math.PI / 180, (angleA-90) * Math.PI / 180, false);
	context.lineTo(100, 100);
	context.fillStyle = multipleArray[0][2];
	context.fill();

	context.beginPath();
	context.arc(100, 100, 100, (angleA - 90) * Math.PI / 180, ((angleA + angleB) - 90) * Math.PI / 180, false);
	context.lineTo(100, 100);
	context.fillStyle = multipleArray[1][2];
	context.fill();

	context.beginPath();
	context.arc(100, 100, 100, ((angleA + angleB) - 90) * Math.PI / 180, ((angleA + angleB + angleC) - 90) * Math.PI / 180, false);
	context.lineTo(100, 100);
	context.fillStyle = multipleArray[2][2];
	context.fill();

	context.beginPath();
	context.arc(100, 100, 100, ((angleA + angleB + angleC) - 90) * Math.PI / 180, ((angleA + angleB + angleC + angleD) - 90) * Math.PI / 180, false);
	context.lineTo(100, 100);
	context.fillStyle = multipleArray[3][2];
	context.fill();

	context.beginPath();
	context.arc(100, 100, 100, ((angleA + angleB + angleC + angleD) - 90) * Math.PI / 180, ((angleA + angleB + angleC + angleD + angleE) - 90) * Math.PI / 180, false);
	context.lineTo(100, 100);
	context.fillStyle = multipleArray[4][2];
	context.fill();

	context.beginPath();
	context.arc(100, 100, 100, ((angleA + angleB + angleC + angleD + angleE) - 90) * Math.PI / 180, ((angleA + angleB + angleC + angleD + angleE + angleF) - 90) * Math.PI / 180, false);
	context.lineTo(100, 100);
	context.fillStyle = multipleArray[5][2];
	context.fill();

	context.closePath();

}

//初期表示用
window.onload = function(){
    drawGraph();
}