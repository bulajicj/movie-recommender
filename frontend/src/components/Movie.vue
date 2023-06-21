<template>
    <b-card class="movie-card">
      <b-card-title>
        {{ movie.title }}
      </b-card-title>
      <b-card-sub-title>
        {{ movie.releaseYear }}
      </b-card-sub-title>
      <b-card-text>
        <b>Cast:</b> {{ movie.cast }}
      </b-card-text>
      <b-card-text style="font-style: italic">
        {{ movie.genres }}
      </b-card-text>
      <!-- <b-card-text>
        <b>Plot:</b> {{ movie.plot }}
      </b-card-text> -->
      <b-card-text>
        <a :href="movie.wikiPage" target="_blank">Wikipedia Page</a>
      </b-card-text>
    </b-card>
  </template>

  <script>
  export default {
    props: {
      movie: {
        type: Object,
        required: true,
      },
    },
  };
  </script>

  <style scoped>
  .movie-card {
    margin-bottom: 20px;
  }
  </style>
