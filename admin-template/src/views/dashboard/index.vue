<template>
  <div class="dashboard-container">
    <div class="dashboard-text">管理员</div>
    <!-- <div @click="test">test</div> -->
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { index } from '@/api/test'
export default {
  name: 'Dashboard',
  computed: {
    ...mapGetters([
      'name'
    ])
  },
  methods:{
    test(){
      index().then( res=> {
        console.log(res)
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
