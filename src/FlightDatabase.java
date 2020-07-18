import java.util.ArrayList;
import java.util.Scanner;

public class FlightDatabase {
    ArrayList<Flight> flightList = new ArrayList<>();
    Scanner input = new Scanner(System.in);

    public FlightDatabase() {
//        this.flightList = flightList;
        flightList.add(new Flight("Berlin", "Tokio", 50));
        flightList.add(new Flight("Paris", "Berlin", 100));
        flightList.add(new Flight("Paris", "Oslo", 100));
        flightList.add(new Flight("Moskwa", "Helsinki", 150));
        flightList.add(new Flight("Oslo", "Nairobi", 200));
        flightList.add(new Flight("Rio", "Denver", 250));
        flightList.add(new Flight("Denver", "Tokio", 300));
        flightList.add(new Flight("Helsinki", "Oslo", 270));
        flightList.add(new Flight("Denver", "Moskwa", 170));
        flightList.add(new Flight("Nairobi", "Berlin", 70));
        flightList.add(new Flight("Denver", "Lizbona", 125));
        flightList.add(new Flight("Lizbona", "Helsinki", 145));
    }

    public void checkIfFlightExist() {
        System.out.println("Podaj lotnisko startowe:");
        String departure = input.nextLine();
        System.out.println("Dokąd lecisz?");
        String arrival = input.nextLine();
        boolean notExist = true;
        for (int i = 0; i < flightList.size(); i++) {
            if (departure.equals(flightList.get(i).getDeparture()) && arrival.equals(flightList.get(i).getArrival())) {
                System.out.println("Taka podróż jest możliwa");
                notExist = false;
            }
        }
        if (notExist) {
            System.out.println("Taki lot nie istnieje");
        }
    }

    public ArrayList<Flight> flightsFromCity() {
        System.out.println("Podaj lotnisko startowe:");
        String departure = input.nextLine();
        ArrayList<Flight> result = new ArrayList<Flight>();
        for (Flight flight : flightList) {
            if (departure.equals(flight.getDeparture())) {
//                System.out.println(flight);
                result.add(flight);
            }
        }
        return result;
    }

    public ArrayList<Flight> flightsToCity() {
        System.out.println("Podaj lotnisko końcowe:");
        String arrival = input.nextLine();
        ArrayList<Flight> result = new ArrayList<Flight>();
        for (Flight flight : flightList) {
            if (arrival.equals(flight.getArrival())) {
//                System.out.println(flight);
                result.add(flight);
            }
        }
        return result;
    }

    public ArrayList<Flight> flightFromTo() {
        System.out.println("Podaj lotnisko startowe:");
        String departure = input.nextLine();
        System.out.println("Podaj lotnisko końcowe:");
        String arrival = input.nextLine();
        ArrayList<Flight> result = new ArrayList<Flight>();
        for (Flight flight : flightList) {
            if (arrival.equals(flight.getArrival()) && departure.equals(flight.getDeparture())) {
                result.add(flight);
            }
        }
        return result;
    }

    public ArrayList<String> getCities() {
        ArrayList<String> cityList = new ArrayList<>();
        for (Flight flight : flightList) {
            if (!cityList.contains(flight.getArrival())) {
                cityList.add(flight.getArrival());
            }
            if (!cityList.contains(flight.getDeparture())) {
                cityList.add(flight.getDeparture());
            }
        }
        return cityList;
    }

    public Flight getCheapestFlight() {
        Flight cheapestFlight = null;
        for (Flight flight : flightList) {
            if (cheapestFlight == null || cheapestFlight.getPrice() > flight.getPrice()) {
                cheapestFlight = flight;
            }
        }
        return cheapestFlight;
    }

    public Flight getCheapestFlightFromCity() {
//        System.out.println("Wyszukaj najtańszy lot. Podaj miasto:");
//        String city = input.nextLine();
        ArrayList<Flight> fromCity = flightsFromCity();
        Flight cheapestFlight = null;
        for (Flight flight : fromCity) {
            if ((cheapestFlight == null || cheapestFlight.getPrice() > flight.getPrice())) {
                cheapestFlight = flight;
            }
        }
        return cheapestFlight;
    }

    public ArrayList<Journey> getFlightWithStop() {
        ArrayList<Flight> startList = flightsFromCity();
        ArrayList<Flight> endList = flightsToCity();
        ArrayList<Journey> flightWithStop = new ArrayList<>();
        for (Flight first : startList) {
            for (Flight second : endList) {
                if (first.getArrival().equals(second.getDeparture())) {
                    flightWithStop.add(new Journey(first, second, first.getPrice() + second.getPrice()));
//                    flightWithStop.add(second);
                }
            }
        }
        return flightWithStop;
    }

    public Journey getCheapestJourney() {
        Journey cheapestJourney = null;
        for (Journey journey : getFlightWithStop()) {
            if (cheapestJourney == null || journey.getPrice() < cheapestJourney.getPrice()) {
                cheapestJourney = journey;
            }
        }
        return cheapestJourney;
    }

    public void getFlightSuper() {
        ArrayList<Flight> startList = flightsFromCity();
        ArrayList<Flight> endList = flightsToCity();
        ArrayList<Flight> flight = new ArrayList<>();
        for (Flight end : endList) {
            for (Flight start : startList) {
                if (end.getArrival().equals(start.getArrival()) && end.getDeparture().equals(start.getDeparture())){
                    flight.add(end);
                }
            }
        }
        if (!flight.isEmpty()) {
            System.out.println(flight);
        }
        if (flight.isEmpty()) {
            ArrayList<Journey> flightWithStop = new ArrayList<>();
            for (Flight first : startList) {
                for (Flight second : endList) {
                    if (first.getArrival().equals(second.getDeparture())) {
                        flightWithStop.add(new Journey(first, second, first.getPrice() + second.getPrice()));
                    }
                }
            }
            System.out.println(flightWithStop);
        }
    }
}