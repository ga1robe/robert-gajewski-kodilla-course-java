package com.kodilla.good.patterns.srp;

public class RentalProcessor {

    private InformationService informationService;
    private RentalService rentalService;
    private RentalRepository rentalRepository;

    public RentalProcessor(MailService mailService, CarRentalService carRentalService, CarRentalRepository carRentalRepository) {
        this.informationService = (InformationService) mailService;
        this.rentalService = (RentalService) carRentalService;
        this.rentalRepository = (RentalRepository) carRentalRepository;
    }


    public void RentalProcessort(final InformationService informationService,
                                 final RentalService rentalService,
                                 final RentalRepository rentalRepository) {
        this.informationService = informationService;
        this.rentalService = rentalService;
        this.rentalRepository = rentalRepository;
    }

    public RentalDto process(final RentRequest rentRequest) {
        boolean isRented = rentalService.rent(rentRequest.getUser(), rentRequest.getFrom(), rentRequest.getTo());
        if (isRented) {
            informationService.inform(rentRequest.getUser());
            rentalRepository.createRental(rentRequest.getUser(), rentRequest.getFrom(), rentRequest.getTo());
            return new RentalDto(rentRequest.getUser(), true);
        } else {
            return new RentalDto(rentRequest.getUser(), false);
        }
    }

}