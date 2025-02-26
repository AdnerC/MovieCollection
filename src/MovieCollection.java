import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MovieCollection
{
  private ArrayList<Movie> movies;
  private Scanner scanner;
  private ArrayList<String> allCastMembers = new ArrayList<String>();
  private ArrayList<String> allGenres = new ArrayList<String>();
  private ArrayList<Double> allRatings = new ArrayList<Double>();
  private ArrayList<Integer> allRevs = new ArrayList<Integer>();

  private ArrayList<Movie> topMoviesRatings = new ArrayList<Movie>();
  private ArrayList<Movie> topMoviesRevs = new ArrayList<Movie>();


  public MovieCollection(String fileName)
  {
    importMovieList(fileName);
    scanner = new Scanner(System.in);
  }

  public ArrayList<Movie> getMovies()
  {
    return movies;
  }
  
  public void menu()
  {
    String menuOption = "";
    
    System.out.println("Welcome to the movie collection!");
    System.out.println("Total: " + movies.size() + " movies");
    
    while (!menuOption.equals("q"))
    {
      System.out.println("------------ Main Menu ----------");
      System.out.println("- search (t)itles");
      System.out.println("- search (k)eywords");
      System.out.println("- search (c)ast");
      System.out.println("- see all movies of a (g)enre");
      System.out.println("- list top 50 (r)ated movies");
      System.out.println("- list top 50 (h)igest revenue movies");
      System.out.println("- (q)uit");
      System.out.print("Enter choice: ");
      menuOption = scanner.nextLine();
      
      if (!menuOption.equals("q"))
      {
        processOption(menuOption);
      }
    }
  }
  
  private void processOption(String option)
  {
    if (option.equals("t"))
    {
      searchTitles();
    }
    else if (option.equals("c"))
    {
      searchCast();
    }
    else if (option.equals("k"))
    {
      searchKeywords();
    }
    else if (option.equals("g"))
    {
      listGenres();
    }
    else if (option.equals("r"))
    {
      listHighestRated();
    }
    else if (option.equals("h"))
    {
      listHighestRevenue();
    }
    else
    {
      System.out.println("Invalid choice!");
    }
  }
  
  private void searchTitles()
  {
    System.out.print("Enter a total search term: ");
    String searchTerm = scanner.nextLine();
    
    // prevent case sensitivity
    searchTerm = searchTerm.toLowerCase();
    
    // arraylist to hold search results
    ArrayList<Movie> results = new ArrayList<Movie>();
    
    // search through ALL movies in collection
    for (int i = 0; i < movies.size(); i++)
    {
      String movieTitle = movies.get(i).getTitle();
      movieTitle = movieTitle.toLowerCase();
      
      if (movieTitle.indexOf(searchTerm) != -1)
      {
        //add the Movie objest to the results list
        results.add(movies.get(i));
      }
    }
    
    // sort the results by title
    sortResults(results);
    
    // now, display them all to the user    
    for (int i = 0; i < results.size(); i++)
    {
      String title = results.get(i).getTitle();
      
      // this will print index 0 as choice 1 in the results list; better for user!
      int choiceNum = i + 1;
      
      System.out.println("" + choiceNum + ". " + title);
    }
    
    System.out.println("Which movie would you like to learn more about?");
    System.out.print("Enter number: ");
    
    int choice = scanner.nextInt();
    scanner.nextLine();
    
    Movie selectedMovie = results.get(choice - 1);
    
    displayMovieInfo(selectedMovie);
    
    System.out.println("\n ** Press Enter to Return to Main Menu **");
    scanner.nextLine();
  }
  
  private void sortResults(ArrayList<Movie> listToSort)
  {
    for (int j = 1; j < listToSort.size(); j++)
    {
      Movie temp = listToSort.get(j);
      String tempTitle = temp.getTitle();
      
      int possibleIndex = j;
      while (possibleIndex > 0 && tempTitle.compareTo(listToSort.get(possibleIndex - 1).getTitle()) < 0)
      {
        listToSort.set(possibleIndex, listToSort.get(possibleIndex - 1));
        possibleIndex--;
      }
      listToSort.set(possibleIndex, temp);
    }
  }
  
  private void displayMovieInfo(Movie movie)
  {
    System.out.println();
    System.out.println("Title: " + movie.getTitle());
    System.out.println("Tagline: " + movie.getTagline());
    System.out.println("Runtime: " + movie.getRuntime() + " minutes");
    System.out.println("Year: " + movie.getYear());
    System.out.println("Directed by: " + movie.getDirector());
    System.out.println("Cast: " + movie.getCast());
    System.out.println("Overview: " + movie.getOverview());
    System.out.println("User rating: " + movie.getUserRating());
    System.out.println("Box office revenue: " + movie.getRevenue());
  }
  
  private void searchCast()
  {
    System.out.print("Enter a total search term: ");
    String searchTerm = scanner.nextLine();

    // prevent case sensitivity
    searchTerm = searchTerm.toLowerCase();

    // arraylist to hold search results
    ArrayList<String> results = new ArrayList<String>();

    // search through ALL movies in collection
    for (int i = 0; i < allCastMembers.size(); i++)
    {
      String actorName = allCastMembers.get(i);
      actorName = actorName.toLowerCase();

      if (actorName.contains(searchTerm))
      {
        //add the actor name to the results list
        results.add(allCastMembers.get(i));
      }
    }

    // sort the results by name
    results.sort(String.CASE_INSENSITIVE_ORDER);
    // now, display them all to the user
    for (int i = 0; i < results.size(); i++)
    {
      String title = results.get(i);

      // this will print index 0 as choice 1 in the results list; better for user!
      int choiceNum = i + 1;

      System.out.println("" + choiceNum + ". " + title);
    }

    System.out.println("Which actor would you like to learn more about?");
    System.out.print("Enter number: ");

    int choice = scanner.nextInt();
    scanner.nextLine();

    ArrayList<Movie> movieResults = new ArrayList<Movie>();

    for(Movie movie:movies){
      if (movie.getCast().contains(results.get(choice-1))){
        movieResults.add(movie);
      }
    }

    for (int i = 0; i < movieResults.size(); i++)
    {
      String title = movieResults.get(i).getTitle();

      // this will print index 0 as choice 1 in the results list; better for user!
      int choiceNum = i + 1;

      System.out.println("" + choiceNum + ". " + title);
    }
//    Movie selectedMovie = results.get(choice - 1);
    System.out.println("Which movie would you like to learn more about?");
    int choice2 = scanner.nextInt();



    displayMovieInfo(movieResults.get(choice2-1));

    System.out.println("\n ** Press Enter to Return to Main Menu **");
    scanner.nextLine();
  }

  private void searchKeywords()
  {
    System.out.print("Enter a title search term: ");
    String searchTerm = scanner.nextLine();

    // prevent case sensitivity
    searchTerm = searchTerm.toLowerCase();

    // arraylist to hold search results
    ArrayList<Movie> results = new ArrayList<Movie>();

    // search through ALL movies in collection
    for (int i = 0; i < movies.size(); i++)
    {
      String movieKeyWords = movies.get(i).getKeywords();
      movieKeyWords = movieKeyWords.toLowerCase();

      if (movieKeyWords.contains(searchTerm))
      {
        //add the Movie objest to the results list
        results.add(movies.get(i));
      }
    }

    // sort the results by title
    sortResults(results);

    // now, display them all to the user
    for (int i = 0; i < results.size(); i++)
    {
      String title = results.get(i).getTitle();

      // this will print index 0 as choice 1 in the results list; better for user!
      int choiceNum = i + 1;

      System.out.println("" + choiceNum + ". " + title);
    }

    System.out.println("Which movie would you like to learn more about?");
    System.out.print("Enter number: ");

    int choice = scanner.nextInt();
    scanner.nextLine();

    Movie selectedMovie = results.get(choice - 1);

    displayMovieInfo(selectedMovie);

    System.out.println("\n ** Press Enter to Return to Main Menu **");
    scanner.nextLine();
  }
  
  private void listGenres()
  {
    allGenres.sort(String.CASE_INSENSITIVE_ORDER);
    for (int i = 0; i < allGenres.size(); i++)
    {
      String title = allGenres.get(i);

      // this will print index 0 as choice 1 in the results list; better for user!
      int choiceNum = i + 1;

      System.out.println("" + choiceNum + ". " + title);
    }
    System.out.println("Which genre would you like to learn more about?");
    System.out.print("Enter number: ");

    int choice = scanner.nextInt();
    scanner.nextLine();

    ArrayList<Movie> results = new ArrayList<Movie>();

    // search through ALL movies in collection
    for (int i = 0; i < movies.size(); i++)
    {
      String movieGenres = movies.get(i).getGenres();
      movieGenres = movieGenres.toLowerCase();

      if (movieGenres.contains(allGenres.get(choice-1).toLowerCase()))
      {
        results.add(movies.get(i));
      }
    }

    for (int i = 0; i < results.size(); i++)
    {
      String title = results.get(i).getTitle();

      // this will print index 0 as choice 1 in the results list; better for user!
      int choiceNum = i + 1;

      System.out.println("" + choiceNum + ". " + title);
    }

    System.out.println("Which movie would you like to learn more about?");
    System.out.print("Enter number: ");

    int choice2 = scanner.nextInt();
    scanner.nextLine();

    Movie selectedMovie = results.get(choice2 - 1);

    displayMovieInfo(selectedMovie);

    System.out.println("\n ** Press Enter to Return to Main Menu **");
    scanner.nextLine();

  
  }
  
  private void listHighestRated()
  {
    for (int i = 0; i < 50; i++)
    {
      String title = topMoviesRatings.get(i).getTitle();

      // this will print index 0 as choice 1 in the results list; better for user!
      int choiceNum = i + 1;

      System.out.println("" + choiceNum + ". " + title+": "+  topMoviesRatings.get(i).getUserRating());
    }

    System.out.println("Which movie would you like to learn more about?");
    System.out.print("Enter number: ");

    int choice = scanner.nextInt();
    scanner.nextLine();

    Movie selectedMovie = topMoviesRatings.get(choice - 1);

    displayMovieInfo(selectedMovie);

    System.out.println("\n ** Press Enter to Return to Main Menu **");
    scanner.nextLine();

  }
  
  private void listHighestRevenue()
  {
    for (int i = 0; i < 50; i++)
    {
      String title = topMoviesRevs.get(i).getTitle();

      // this will print index 0 as choice 1 in the results list; better for user!
      int choiceNum = i + 1;

      System.out.println("" + choiceNum + ". " + title+": $"+  topMoviesRevs.get(i).getRevenue());
    }

    System.out.println("Which movie would you like to learn more about?");
    System.out.print("Enter number: ");

    int choice = scanner.nextInt();
    scanner.nextLine();

    Movie selectedMovie = topMoviesRevs.get(choice - 1);

    displayMovieInfo(selectedMovie);

    System.out.println("\n ** Press Enter to Return to Main Menu **");
    scanner.nextLine();
  
  }
  
  private void importMovieList(String fileName)
  {
    try
    {
      FileReader fileReader = new FileReader(fileName);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      String line = bufferedReader.readLine();
      
      movies = new ArrayList<Movie>();


      while ((line = bufferedReader.readLine()) != null) 
      {
        String[] movieFromCSV = line.split(",");
     
        String title = movieFromCSV[0];
        String cast = movieFromCSV[1];
        String director = movieFromCSV[2];
        String tagline = movieFromCSV[3];
        String keywords = movieFromCSV[4];
        String overview = movieFromCSV[5];
        int runtime = Integer.parseInt(movieFromCSV[6]);
        String genres = movieFromCSV[7];
        double userRating = Double.parseDouble(movieFromCSV[8]);
        int year = Integer.parseInt(movieFromCSV[9]);
        int revenue = Integer.parseInt(movieFromCSV[10]);

        String[] castMembers = cast.split("\\|");
        String[] movieGenres = genres.split("\\|");

        allRatings.add(userRating);
        allRevs.add(revenue);

        for (String actor : castMembers){
          if (!allCastMembers.contains(actor)){
            allCastMembers.add(actor);
          }
        }

        for (String genre : movieGenres){
          if (!allGenres.contains(genre)){
            allGenres.add(genre);
          }
        }

        Movie nextMovie = new Movie(title, cast, director, tagline, keywords, overview, runtime, genres, userRating, year, revenue);
        movies.add(nextMovie);
      }

      ArrayList <Double> ratings = new ArrayList<Double>();
      Collections.sort(allRatings);
      for (int i =0; i<50;i++){
        if(!ratings.contains(allRatings.get(allRatings.size()-1-i))){
          ratings.add(allRatings.get(allRatings.size()-1-i)) ;
        }
      }


      ArrayList<Movie> topMoviesRating = new ArrayList<Movie>();

      for(Double rate : ratings){
        // search through ALL movies in collection
        for (int i = 0; i < movies.size(); i++)
        {
          Double movieRating = movies.get(i).getUserRating();

          if (Objects.equals(rate, movieRating))
          {
            topMoviesRating.add(movies.get(i));
          }
        }
      }
      topMoviesRatings=topMoviesRating;


      //REVENUE
      ArrayList <Integer> revs = new ArrayList<Integer>();
      Collections.sort(allRevs);
      for (int i =0; i<50;i++){
        if(!revs.contains(allRevs.get(allRevs.size()-1-i))){
          revs.add(allRevs.get(allRevs.size()-1-i)) ;
        }
      }


      ArrayList<Movie> topMoviesRev = new ArrayList<Movie>();

      for(int rev : revs){
        // search through ALL movies in collection
        for (int i = 0; i < movies.size(); i++)
        {
          int movieRev = movies.get(i).getRevenue();

          if (Objects.equals(rev, movieRev))
          {
            topMoviesRev.add(movies.get(i));
          }
        }
      }
      topMoviesRevs=topMoviesRev;
      bufferedReader.close();
    }
    catch(IOException exception)
    {
      // Print out the exception that occurred
      System.out.println("Unable to access " + exception.getMessage());              
    }
  }
}