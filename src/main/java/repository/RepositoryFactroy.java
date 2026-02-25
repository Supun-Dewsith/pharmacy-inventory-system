package repository;

import repository.custom.SuplierRepository;
import repository.custom.impl.MedicineRepositoryImpl;
import repository.custom.impl.OrderRepositoryImpl;
import repository.custom.impl.RecentActivityRepositoryImpl;
import repository.custom.impl.SuplierRepositoryImpl;
import util.RepositoryType;

public class RepositoryFactroy {
    private static RepositoryFactroy instance;

    private final MedicineRepositoryImpl medicineRepository;
    private final OrderRepositoryImpl orderRepository;
    private final RecentActivityRepositoryImpl recentActivityRepository;
    private final SuplierRepositoryImpl suplierRepository;

    private RepositoryFactroy(){
        medicineRepository=new MedicineRepositoryImpl();
        orderRepository=new OrderRepositoryImpl();
        recentActivityRepository=new RecentActivityRepositoryImpl();
        suplierRepository=new SuplierRepositoryImpl();
    }

    public static RepositoryFactroy getInstance(){return instance==null?instance=new RepositoryFactroy():instance;}

    public <T extends SuperRepository>T getRepositoryType(RepositoryType repositoryType){
        switch (repositoryType){
            case MEDICINE: return (T) medicineRepository;
            case ORDER:return (T) orderRepository;
            case RECENTACTIVITY:return (T) recentActivityRepository;
            case SUPLIER:return (T) suplierRepository;
        }
        return null;
    }
}
