<template>
  <div>
    <NewSongsItem />
  </div>
</template>


<script>
import { getNewSongs } from "../../network/newsongs";
import NewSongsItem from "./NewSongsItem";
export default {
  name: "NewSongs",
  components: {
    NewSongsItem
  }
};
</script>



<style scoped>
</style>