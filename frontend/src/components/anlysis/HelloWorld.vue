<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <p>
      Server response : {{greetingMessage}}
      <br>
      sample name : {{name}}
      <br>
      sample age : {{age}}
    </p>    
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  props: {
    msg: String
  },
  data () {
    return {
      greetingMessage: '',
      name: '',
      age: 0
    }
  },
  created() {
    this.$http.get('/api/analysis/greeting')
      .then(response => {
        this.greetingMessage = response.data.body;
      })
      .catch(e => {
        console.log('error : ', e)
      })

      this.$http.get('/api/analysis/all')
      .then(response => {
        let sample = response.data.body;
        this.name = sample.name;
        this.age = sample.age;
      })
      .catch(e => {
        console.log('error : ', e)
      })
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
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
