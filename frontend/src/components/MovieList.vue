<template>
    <div class="movie-list">
      <b-row>
        <b-col v-for="movie in movies" :key="movie.id" md="6" class="mb-3">
          <Movie :movie="movie" />
        </b-col>
      </b-row>
    </div>
  </template>
  
  <script>
  import Movie from './Movie.vue';
  
  export default {
    props: {
      movies: {
        type: Array,
        required: true,
      },
    },
    components: {
      Movie,
    },
  };
  </script>
  
  <style scoped>
  .movie-list {
    padding: 20px;
  }
  </style>