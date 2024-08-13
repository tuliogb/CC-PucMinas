class Sistema{

    public static void main(String[] args){
        Estacionamento um = new Estacionamento("00530065694", 6, "Jose Augusto", "QXR2847");
        Estacionamento dois = new Estacionamento("02138567411", 48, "Luiz Paulo", "SUV2299");
        Estacionamento tres = new Estacionamento("18620022299", 12, "Stefen Curry", "RTA7211");

        um.checkOut();
        dois.checkOut();
        tres.checkOut();

        um.armazenar();
        dois.armazenar();
        tres.armazenar();

        //Esta sobreescrevendo porque o ponteiro de escrita esta se mantendo no inicio.

    }   
}