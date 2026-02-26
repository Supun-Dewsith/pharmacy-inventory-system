package repository;

import repository.custom.SuplierOrderRepository;
import repository.custom.impl.*;
import util.RepositoryType;

public class RepositoryFactroy {
    private static RepositoryFactroy instance;

    private final MedicineRepositoryImpl medicineRepository;
    private final BuyerOrderRepositoryImpl buyerOrderRepository;
    private final RecentActivityRepositoryImpl recentActivityRepository;
    private final SuplierRepositoryImpl suplierRepository;
    private final SuplierOrderRepository suplierOrderRepository;

    private RepositoryFactroy(){
        medicineRepository=new MedicineRepositoryImpl();
        buyerOrderRepository=new BuyerOrderRepositoryImpl();
        recentActivityRepository=new RecentActivityRepositoryImpl();
        suplierRepository=new SuplierRepositoryImpl();
        suplierOrderRepository=new SuplierOrderRepositoryImpl();
    }

    public static RepositoryFactroy getInstance(){return instance==null?instance=new RepositoryFactroy():instance;}

    public <T extends SuperRepository>T getRepositoryType(RepositoryType repositoryType){
        switch (repositoryType){
            case MEDICINE: return (T) medicineRepository;
            case BUYERORDER:return (T) buyerOrderRepository;
            case RECENTACTIVITY:return (T) recentActivityRepository;
            case SUPLIER:return (T) suplierRepository;
            case SUPLIERORDER:  return (T) suplierOrderRepository;
        }
        return null;
    }
}
