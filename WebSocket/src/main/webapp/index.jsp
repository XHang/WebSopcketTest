<html>
<body>
<h2>WebSocket客户端</h2>
<script type="text/javascript">
	//新建一个WebSocket对象,socket的协议一般以ws开头，安全的协议以wss开头
	var ws=new WebSocket("ws://webSocket/echo");
	//
	ws.onopen = function (){
		ws.send("Test!");
	};
	ws.onmessage = function(evt){
		console.log(evt.data);ws.close(200,"正常关闭");
		}; 
	ws.onclose = function(evt){
			console.log("WebSocketClosed!");
	}; 
	ws.onerror = function(evt){
		console.log("WebSocketError!");
	};
</script>
</body>
</html>
