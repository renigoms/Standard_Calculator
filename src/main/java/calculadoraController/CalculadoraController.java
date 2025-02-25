package calculadoraController;

import calculadoraView.PainelDeDigitos;
import calculadoraView.TelaPrincipal;
import calculadoraservice.CalculadoraService;
import calculadoraservice.point.PointManager;
import calculadoraservice.tecladoservice.TecladoActions;

public class CalculadoraController {
    private final PainelDeDigitos digitos;

    private PointManager pointManager;

    private CalculadoraService calculadoraService;


    private TecladoActions tecladoActions;

    public CalculadoraController(TelaPrincipal telaPrincipal) {
        super();
        this.digitos = telaPrincipal.getPainelDigitos();
        this.pointManager = new PointManager();
        calculadoraService = new CalculadoraService(telaPrincipal, pointManager, this);
        this.tecladoActions = new TecladoActions(telaPrincipal, this, calculadoraService, pointManager);
        calculadoraService.control();
        tecladoActions.control();
        digitos.getLigar().doClick();
    }

}