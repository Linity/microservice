<template>
  <div>
    <h1>测试webSocket</h1>
    <button @click="getWebsocket">点击请求后台数据</button>
  </div>
</template>

<script>
import axios from "../../utils/axios";
export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  },
  created() { // 页面创建生命周期函数
    this.initWebSocket()
  },
  destroyed: function () { // 离开页面生命周期函数
    this.websocketclose();
  },
  methods: {
    initWebSocket: function () {
      // WebSocket与普通的请求所用协议有所不同，ws等同于http，wss等同于https
//      this.websock = new WebSocket("ws://localhost:8088/learn/websocket/1");
      this.websock = new WebSocket("ws://10.10.103.168:8020/effengine/websocket");
      this.websock.onopen = this.websocketonopen;
      this.websock.onerror = this.websocketonerror;
      this.websock.onmessage = this.websocketonmessage;
      this.websock.onclose = this.websocketclose;
    },
    websocketonopen: function () {
      console.log("WebSocket连接成功");
    },
    websocketonerror: function (e) {
      console.log("WebSocket连接发生错误");
    },
    websocketonmessage: function (e) {
      console.log(e.data);                // console.log(e);
    },
    websocketclose: function (e) {
      console.log("connection closed (" + e.code + ")");
    },
    getWebsocket: function () {
      axios.get("/learn/test/websocket").then(data =>{
          console.log(data)
      })
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
