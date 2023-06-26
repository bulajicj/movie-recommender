# movie-recommender
**Prerequisites**
1. Appropriate Docker version installed - we have used the latest one (24.0.2)
2. Docker Desktop has to be opened while building images from terminal

**How to run?**
To run this project locally, follow next steps:

1. After downloading zip folder from Git or cloning the repository, you can open it from Command Prompt, Intelij, Virtual Studio Code etc.
2. Run **docker-compose build** , this command will generate backend and frontend images.
3. After images have been created, run **docker-compose up** , which will start the application.
4. Once the containers are up and running, you can access the application in your web browser at **http://localhost:8080/**


![Screenshot 2023-06-26 124346](https://github.com/bulajicj/movie-recommender/assets/135854133/9eb57b08-933b-4853-81ef-fd69fd6efee8)

Search by title: 
![Screenshot 2023-06-26 124415](https://github.com/bulajicj/movie-recommender/assets/135854133/b4e033b5-988c-41ca-88b0-7249cdecdb53)

Search by title and year range: 
![Screenshot 2023-06-26 124530](https://github.com/bulajicj/movie-recommender/assets/135854133/23d7980c-2367-4de8-9d0f-1e3146869500)

Empty list if there are no movies that match the requirements(horror genre added); user is notified by alert before returning the empty list: 
![Screenshot 2023-06-26 130724](https://github.com/bulajicj/movie-recommender/assets/135854133/af5d4422-1f2c-44bd-b092-db4befca9803)

Search by genres:
![image](https://github.com/bulajicj/movie-recommender/assets/135854133/2f75ad9d-0a75-41f2-9023-a35b156c4648)


Search by actors: 
![Screenshot 2023-06-26 124633](https://github.com/bulajicj/movie-recommender/assets/135854133/43e1ab59-24a9-442c-b2ed-48e611a1e387)

Search by words that describe the plot the most: 
![Screenshot 2023-06-26 125428](https://github.com/bulajicj/movie-recommender/assets/135854133/8783805a-0b42-4546-8c24-82e5ddb3aa31)











