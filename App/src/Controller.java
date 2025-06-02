public class Controller {
    /**
     * Se usa desde el model para enviar mensajes
     * @param texto
     * @param error
     */
    public static void enviarMsg(String texto, boolean error) {
        View.msg(texto,error);
    }
}
