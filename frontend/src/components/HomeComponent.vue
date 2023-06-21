<template>
  <div class="container">
    <div class="content-wrapper">
    <h1 class="font-weight-bold">MOVIE RECOMMENDER</h1>
    <hr/>
    
    <b-form @submit.prevent="submitForm">
      <b-row>
        <b-col md="6">
          <b-form-group label="Title" label-for="title-input">
            <b-form-input id="title-input" v-model="title" placeholder="Enter words present in title"></b-form-input>
          </b-form-group>

          <b-form-group>
            <b-form-radio-group v-model="yearOption">
              <b-form-radio value="single">Single Year</b-form-radio>
              <b-form-radio value="range">Year Range</b-form-radio>
            </b-form-radio-group>
          </b-form-group>

          <b-form-group v-if="yearOption === 'single'" label="Year" label-for="year-input">
            <b-form-select id="year-input" v-model="year" :options="yearOptions"></b-form-select>
            
          </b-form-group>

          <b-form-group v-if="yearOption === 'range'" label="Year Range">
            <div>
              <b-form-select v-model="yearFrom" :options="yearOptions"></b-form-select>
              <span class="mx-2">to</span>
              <b-form-select v-model="yearTo" :options="yearToOptions"></b-form-select>
            </div>
            
          </b-form-group>
          
            <b-form-group label="Genres">
              <div>
                <b-form-checkbox-group v-model="genres">
                  <b-form-checkbox v-for="genreOption in genreOptions" :key="genreOption" :value="genreOption">
                    {{ genreOption }}
                  </b-form-checkbox>
                </b-form-checkbox-group>
              </div>
            </b-form-group>
          
        </b-col>

        <b-col md="6">
          <b-form-group label="Cast">
            <b-form-textarea v-model="cast" placeholder="Enter actors separated by commas" rows="2"></b-form-textarea>
          </b-form-group>

          <b-form-group label="Plot" label-for="plot-input">
            <b-form-textarea id="plot-input" v-model="plot" placeholder="Enter keywords describing the plot" rows="3"></b-form-textarea>
          </b-form-group>

          <b-form-group label="Number of Hits" label-for="hits-input" required>
            <b-form-input id="hits-input" v-model="hits" type="number" required></b-form-input>
          </b-form-group>
        </b-col>
      </b-row>

      <b-button type="submit">Search</b-button>
    </b-form>


  
    <MovieList :movies="moviesData" />
  </div>
</div>
</template>

<script>
import axios from 'axios';
import MovieList from './MovieList.vue';

export default {
  components: {
    MovieList,
  },
  data() {
    return {
      title: null,
      yearOption: 'single',
      year: null,
      yearFrom: null,
      yearTo: null,
      genres: [],
      cast: null,
      plot: null,
      hits: 30,
      yearOptions: generateYearOptions(1903, 2017),
      genreOptions: ['action','adventure','animation','anime','biography','comedy','concert','crime','documentary','drama','family','fantasy','film-noir','history', 'horror','music','musical','mystery','period','psychological','romance','science-fiction','sport','thriller','war','western'],
      moviesData: []
    };
  },

  computed: {
  yearToOptions() {
    if (this.yearOption === 'range' && this.yearFrom) {
      const startYear = parseInt(this.yearFrom) + 1;
      return this.yearOptions.filter(option => option.value >= startYear);
    }
    return [];
  },
},
  methods: {
  
    submitForm() {
  
      
      const queryParams = {
        yearQuery: this.yearOption==="single" ? this.year : this.yearFrom != null ?  `${this.yearFrom} TO ${this.yearTo}` : null ,
        titleQuery: this.title ? (this.title.trim().length > 0 ? this.title : null) : null ,
        castQuery:  this.cast ? this.cast.split(',').map(actor => `"${actor.trim()}"`  ).join(' AND ') : null,
        genresQuery: this.genres.length > 0 ? this.genres.join(' AND ') : null,
        plotQuery: this.plot ? (this.plot.trim().length > 0 ? this.plot : null) : null,
        numOfHits: this.hits
      };

      axios.get('search', { params: queryParams })
        .then(response => {
          this.moviesData = response.data;
          //console.log(queryParams)
          console.log(response.data);
        })
        .catch(error => {
          
          if(error.response.status === 404){
            this.moviesData = [];
            alert("There aren't movies that satisfy your requirements")
          } 
          else alert("Something went wrong, try again.")
        });
    }
  }

  
};

function generateYearOptions(startYear, endYear) {
  const options = [{ value: null, text: '' }];
  for (let year = startYear; year <= endYear; year++) {
    options.push({ value: year, text: year.toString() });
  }
  return options;
}
</script>
<style>
.container {
  padding: 10px;
}

.content-wrapper {
  margin: 0 auto;
  /* max-width: 800px;  */
}



</style>
