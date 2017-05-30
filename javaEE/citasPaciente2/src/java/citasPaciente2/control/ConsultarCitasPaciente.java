package citasPaciente2.control;

import citasPaciente2.modelo.Cita;
import citasPaciente2.modelo.Paciente;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;


@WebServlet(name = "ConsultarCitasPaciente", urlPatterns = {"/ConsultarCitasPaciente"})
public class ConsultarCitasPaciente extends HttpServlet {
    
    @PersistenceUnit
    private EntityManagerFactory emf;
    @Resource
    private UserTransaction utx;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        emf = Persistence.createEntityManagerFactory("citasPaciente2PU2");
        CitaJpaController controlCita = new CitaJpaController(utx, emf);
        PacienteJpaController controlPaciente = new PacienteJpaController(utx, emf);
        List<Cita> listaCitas = controlCita.findCitaEntities();
        
        int idPaciente = Integer.parseInt(request.getParameter("idPaciente"));
        Paciente p = controlPaciente.findPaciente(idPaciente);
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ConsultarCitas</title>");            
            out.println("</head>");
            out.println("<body>");
            
            out.println("<h1>Paciente: "+p.getNombre()+"</h1>");
            out.println("<table aling='left' width='60%' border=1>");
            out.println(
                "<tr><td class='datos'>Fecha</td>"
                +"<td class='datos'>Hora</td></tr>"
                );
            
            for(Cita c : listaCitas){
                
                if(c.getPaciente().getIdpaciente() == idPaciente){
                    String fecha = c.getFecha().toString();
                    String hora = c.getHora().toString();
                    System.out.println(fecha);
                    System.out.println(hora);
                    //iniciando la tabla
                    out.println("<tr><td class='datos'>"+fecha+"</td>"
                      +"<td class='datos'>"+hora+"</td>"
                      );
                }
            } //for
            out.println("</table>");
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}