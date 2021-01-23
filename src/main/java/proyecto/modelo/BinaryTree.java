package proyecto.modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import proyecto.constantes.Constantes;
import static proyecto.constantes.Constantes.PATH_DATOS;
import proyecto.modelo.Nodo;

/**
 * 
 * @author KevinChevez
 */
public class BinaryTree<E> {
    private Nodo<E> root;
    private Nodo<E> nodoActual;
    
    /**
     * Nodo queda vacio
     */
    public BinaryTree(){
    }    
    
    /**
     * Metodo que verifica si el arbol está vacio
     * @return true si el arbol está vacio y false, si no lo está
     */
    public boolean isEmpty(){
        return root == null;
    }
    
    public boolean add(E child, E parent){
        Nodo<E> nchild = new Nodo<>(child);
        if(isEmpty() && parent == null){
            root = nchild;
            return true;
        }
        //add(B,A)
        Nodo<E> nParent = searchNode(parent);
        Nodo<E> nChildExist = searchNode(child);
        if(nChildExist == null && nParent != null){
            if(nParent.getLeft() == null){
                nParent.setLeft(nchild); 
                return true;
            }
            else if(nParent.getRight() == null){
                nParent.setRight(nchild);
                return true;
            }
        }
        return false;
    }     
    
    public boolean remove(E data){
        Nodo<E> parent = searchParent(data);
        if(parent == null){
            return false;
        }
        if(parent.getLeft()!=null && parent.getLeft().getData().equals(data)){
            parent.setLeft(null);
        }
        else{
            parent.setRight(null);
        }
        return true;
    }
    private Nodo<E> searchParent(E data){
        return searchParent(data, root);
    }
    private Nodo<E> searchParent(E data, Nodo<E> n){
        if(n == null){
            return n;
        }
        else if((n.getLeft()!=null && n.getLeft().getData().equals(data)) || (n.getRight()!= null && n.getRight().getData().equals(data))){
            //System.out.println("Devuelve el arbol p: "+n.data);
            return n;
        }
        else{ //hay que seguir descendiendo
            Nodo<E> restultLeft = searchParent(data, n.getLeft());
            if(restultLeft!=null) return restultLeft;
            return searchParent(data, n.getRight());
        }
    }
    
    private Nodo<E> searchNode(E data){
        return searchNode(data, root);
    }
    
    /**
     * Funcion que ayuda a la recursividad y envia Nodo que irá cambiando por etapa
     * @param data Recibe la data a buscar
     * @param p Recibe el nodo a buscar en seccion
     * @return Devuelve el nodo que encontró y null si no encontró
     */
    private Nodo<E> searchNode(E data, Nodo<E> p){
        //Caso base 1
        if(p==null){
            return null; //tambien puede retornar p
        }
        //Caso base 2
        else if(p.getData().equals(data)){
            return p; // devuelve el nodo investigado
        }
        //Caso recursivo
        else{
            Nodo<E> restultLeft = searchNode(data, p.getLeft());
            if(restultLeft!=null) return restultLeft;
            return searchNode(data, p.getRight());
        }
    }
    
    public boolean add(E parent, E data, boolean insertIzq){
        Nodo<E> hijo = new Nodo<>(data);
        if(parent == null && isEmpty()){
            root = hijo;
            return true;
        }
        Nodo<E> nParent = searchNodo(parent);
        if(nParent==null){
            return false;
        }
        else if(nParent.isComplete()){
            return false;
        }        
        else if(nParent.isHoja()){
            if(insertIzq)
                nParent.setLeft(hijo);
            else
                nParent.setRight(hijo);
        }
        else{
            if(insertIzq){
                Nodo<E> n = (nParent.getLeft()==null)? hijo:nParent.getLeft();
                nParent.setLeft(n);
            }
            else{
                Nodo<E> n = (nParent.getRight()==null)? hijo:nParent.getRight();
                nParent.setRight(n);
            }
                
        }
        return true;
    }
    
    public Nodo<E> searchNodo(E data){
        return (data!=null)? searchNodo(data, root) : null;
    }
    private Nodo<E> searchNodo(E data, Nodo<E> n){
        if(n == null){
            return null;
        }
        else if(n.getData().equals(data) && !n.isComplete()){
            return n;
        }
        else{
            Nodo<E> izqResult = searchNodo(data, n.getLeft());
            return (izqResult!=null)? izqResult : searchNodo(data, n.getRight());
        }
    }
    
    public void anchura(){
        if(!isEmpty()){
            Queue<Nodo<E>> cola = new LinkedList<>();
            cola.offer(root);
            while(!cola.isEmpty()){
                Nodo<E> n = cola.poll();
                System.out.print(n.getData()+" ");
                if(n.getLeft()!=null) cola.offer(n.getLeft());
                if(n.getRight()!=null) cola.offer(n.getRight());
            }
        }
        System.out.println("");
    }
    
    public void preOrden(){
        preOrden(root);
    }
    private void preOrden(Nodo<E> parent){
        if(parent!=null){
            System.out.print(parent.getData()+" ");
            preOrden(parent.getLeft());
            preOrden(parent.getRight());
        }
    }
    
    public void inOrden(){
        inOrden(root);
    }
    private void inOrden(Nodo<E> parent){
        if(parent!=null){
            inOrden(parent.getLeft());
            System.out.print(parent.getData()+" ");
            inOrden(parent.getRight());
        }
    }
    
    public void postOrden(){
        postOrden(root);
    }
    private void postOrden(Nodo<E> parent){
        if(parent!=null){
            postOrden(parent.getLeft());
            postOrden(parent.getRight());
            System.out.print(parent.getData()+" ");
        }
    }
    
    public int size(){
        return size(root);
    }
    private int size(Nodo<E> n){
        if(n == null){
            return 0;
        }
        return 1+size(n.getLeft())+size(n.getRight());
    }
    
    public int height(){
        return height(root);
    }
    private int height(Nodo<E> n){
        if(n == null){
            return 0;
        }
        return 1 + Math.max(height(n.getLeft()), height(n.getRight()));
    }
    
    public int countLeaves(){
        return countLeaves(root);
    }
    private int countLeaves(Nodo<E> n){
        if(n == null){
            return 0;
        }
        else if((n.getLeft() == null) && (n.getRight()==null)){
            return 1;
        }
        else{
            return countLeaves(n.getLeft())+countLeaves(n.getRight());
        }
    }
    
    public boolean isFull(){
        return isFull(root);
    }
    private boolean isFull(Nodo<E> n){
        if(n == null){
            return true;
        }
        if((n.getLeft() == null && n.getRight() ==null) || (n.getLeft() == null && n.getRight() == null)){
            return false;
        }
        return isFull(n.getLeft()) && isFull(n.getRight()) && height(n.getLeft())== height(n.getRight());
    }
    
    public String decision(List<String> respuestas){     
        Iterator<String> it = respuestas.iterator();
        return (respuestas!=null)? decision(it, (Nodo<String>)root) : "No hay Lista";
    }
    private String decision(Iterator<String> it, Nodo<String> parent){
        boolean tiene=it.hasNext();
        String valor = (it.hasNext())? it.next() : null;
        boolean va=valor!=null;
        boolean derecha = (valor!=null)? (valor.toLowerCase().equals("yes")) : false;
        if(parent == null || valor==null){
            return "INCERTIDUMBRE";
        }
        else if(derecha && parent.getRight().isHoja()){
            return parent.getRight().getData();
        }
        else if(!derecha && parent.getLeft().isHoja()){
            return parent.getLeft().getData();
        }
        else{
            return (derecha)? decision(it, parent.getRight()): decision(it, parent.getLeft());
        }
    }
    
    public boolean addMorse(E child, List<String> direcciones){
        this.root = (this.root == null)? (Nodo<E>)(new Nodo<String>("INICIO")) : this.root;
        Iterator<String> it = direcciones.iterator();
        if(child!=null && direcciones!=null){
            addMorse(child, it, root);
            return true;
        }
        return false;    
    }
    private Nodo<E> addMorse(E child, Iterator<String> it, Nodo<E> parent){
        Nodo<E> nodoHijo = new Nodo<>(child);        
        Nodo<String> nodoVacio =  new Nodo<>("*");
        String direccion = (it.hasNext())? it.next() : null;
        if((direccion!=null)&& direccion.equals("-")){
            if(parent.getLeft()==null){                
                Nodo<E> nodo = (it.hasNext())? addMorse(child, it, (Nodo<E>)nodoVacio) : nodoHijo;
                parent.setLeft(nodo);
            }
            else{
                nodoHijo.setRight(parent.getLeft().getRight());
                nodoHijo.setLeft(parent.getLeft().getLeft());
                Nodo<E> nodo =(it.hasNext())? addMorse(child, it, parent.getLeft()) : nodoHijo;
                parent.setLeft(nodo);
            }
        }
        else if((direccion!=null)&& direccion.equals(".")){
            if(parent.getRight()==null){
                Nodo<E> nodo = (it.hasNext())? addMorse(child, it, (Nodo<E>)nodoVacio) : nodoHijo;
                parent.setRight(nodo);
            }
            else{
                nodoHijo.setRight(parent.getRight().getRight());
                nodoHijo.setLeft(parent.getRight().getLeft());
                Nodo<E> nodo = (it.hasNext())? addMorse(child, it, parent.getRight()) : nodoHijo;
                parent.setRight(nodo);
            }
        }                       
        return parent;
    }
    
    public String codifyMorse(List<String> codigos){
        Iterator<String> it = codigos.iterator();
        return codifyMorse(it, root);
    }
    private String codifyMorse(Iterator<String> it, Nodo<E> parent){
        StringBuilder resp = new StringBuilder();        
        if(parent != null){
            if(parent.isHoja()){
                resp.append(parent.getData());
                resp.append(codifyMorse(it, root));
            }
            String code = (it.hasNext())? it.next() : null;
            if(code!=null && code.equals("-")){
                resp.append(codifyMorse(it, parent.getLeft()));
            }
            else if(code!=null && code.equals(".")){
                resp.append(codifyMorse(it, parent.getRight()));
            }
        }
        else if(it.hasNext()){
            resp.append(codifyMorse(it, root));
        }
        return resp.toString();
    }
    
    public List<E> equiposDerrotadosFases (String fase, HashMap<String, Integer> fases){
        Integer lvlArb = fases.get(fase);
        return (lvlArb!=null)? equiposDerrotadosFases(root, lvlArb, 0) : null;
    }
    private List<E> equiposDerrotadosFases (Nodo<E> parent, Integer lvlArb, Integer lvlAct){
        List<E> resp = new ArrayList<>();
        if(parent!=null){
            lvlAct+=1;
            if(lvlArb < lvlAct) resp.add(parent.getRight().getData());
            if(!parent.getLeft().isHoja()){
                resp.addAll(equiposDerrotadosFases(parent.getLeft(), lvlArb, lvlAct));
            }
            if(!parent.getRight().isHoja()){
                resp.addAll(equiposDerrotadosFases(parent.getRight(), lvlArb, lvlAct));
            }
        }
        return resp;
    }
    
    public List<E> equiposEliminados(E seleccion){
        return equiposEliminados(seleccion, root);
    }
    private List<E> equiposEliminados(E seleccion, Nodo<E> parent){
        List<E> resp = new ArrayList<>();
        if((seleccion!=null && parent!=null) && !parent.isHoja()){ 
            if( parent.getData().equals(seleccion)){
                resp.add(parent.getRight().getData());
                resp.addAll(equiposEliminados(seleccion, parent.getLeft()));
            }
            else{ // ayudo a evitar la búsqueda a ciegas
                if(parent.getLeft().getData().equals(seleccion)){
                    resp.addAll(equiposEliminados(seleccion, parent.getRight()));
                }
                else if(parent.getLeft().getData().equals(seleccion)){
                    resp.addAll(equiposEliminados(seleccion, parent.getLeft()));
                }
                else{
                    resp.addAll(equiposEliminados(seleccion, parent.getLeft()));
                    resp.addAll(equiposEliminados(seleccion, parent.getRight()));
                }
            }
        }
        return resp;
    }
    
    public boolean isBST(){
        Comparator<E> comparador = (E o1, E o2) -> o1.hashCode()-o2.hashCode();
        return isBST(root, comparador);
    }
    private boolean isBST(Nodo<E> parent, Comparator<E> comparador){
        if(parent==null){
            return true;
        }
        if(parent.isComplete()){
            return (comparador.compare(parent.getData(), parent.getLeft().getData())>0 
                    && comparador.compare(parent.getData(), parent.getRight().getData())<0 
                    && isBST(parent.getLeft(), comparador)
                    && isBST(parent.getRight(), comparador));
        }
        else if(!parent.isHoja()){
                return (parent.getLeft()!=null)? 
                        (comparador.compare(parent.getData(), parent.getLeft().getData())>0 && isBST(parent.getLeft(), comparador))
                        :(comparador.compare(parent.getData(), parent.getRight().getData())<0 && isBST(parent.getRight(), comparador));
        }
        else{// si es hoja
            return true;
        } 
    }
    
    public void crearArbolHeap(int inicio, int n){
        root = (Nodo<E>)crearArbolHeap(inicio, n, root);
    }
    private Nodo<E> crearArbolHeap(int inicio, int n, Nodo<E> parent) {
        if(n>0){
            Nodo<Integer> raiz = new Nodo<>(inicio);
            if(parent == null){
                parent = (Nodo<E>)raiz;
            }
            parent.setLeft(crearArbolHeap((inicio*2)+1, n-1, parent.getLeft()));
            parent.setRight(crearArbolHeap((inicio*2)+2, n-1, parent.getRight()));
        }
        return parent;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.root);
        return hash;
    }
    
     /**
     * Metodo équals que verifica si un objeto tipo BinaryTree es igual a otra
     * pasado como parámetro, a partir de su altura y data de cada nodo en el
     * arbol.
     * @param obj Recibe un Object a ser comparado.
     * @return Devuelve true si el objeto pasado como es el mismo o son idénticos.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BinaryTree<E> other = (BinaryTree<E>) obj;        
        return (this.height() == other.height())? equals(this.root, other.root) : false;
    }
    private boolean equals(Nodo<E> thisParent, Nodo<E> otherParent){        
        if(thisParent == null && otherParent==null){
            return true;
        }
        if(thisParent != null && otherParent!=null){
            return thisParent.getData().equals(otherParent.getData()) &&
                    equals(thisParent.getLeft(), otherParent.getLeft()) &&
                    equals(thisParent.getRight(), otherParent.getRight());
        }
        return false;
    }
    
    public Nodo<E> getRoot(){
        return root;
    }
    
    /**
     * Método que se encarga de construir el arbol usado en la aplicacion
     * 'Genio Politécnico'.
     * @param path Recibe como parámetro la ubicación del archivo ubicado en
     * el repositorio local
     */
    public void construirArbolGenio(String path){
        try(Scanner sc = new Scanner(new File(path))){
            String line = sc.nextLine();
            String[] valores = line.split(" ");
            String mensaje = construirMensaje(valores, 1);  
            mensaje = (mensaje.substring(0, 1).toUpperCase()+mensaje.substring(1).toLowerCase());
            this.root = new Nodo<>((E)mensaje);
            root.setLeft(construirArbolGenio(sc));
            root.setRight(construirArbolGenio(sc));
            this.nodoActual = this.root;
        }catch(IOException e){
            System.out.println("El archivo no se puede abrir error: "+e.getMessage());
        }
    }
    
    /**
     * Metodo auxiliar recursivo del método principal que se encarga de construir
     * la estructura de dato arbol binario para la aplicación 'Genio Politécnico'.
     * @param sc Recibe como parámetro un Scanner que se encargará de ir leyendo
     * linea a linea el archivo de manera recursiva.
     * @return Devuelve el nodo cosntruido con a partir de los parámetros dados.
     */
    private Nodo<E> construirArbolGenio(Scanner sc){  
        String line = sc.nextLine();
        String[] valores = line.split(" ");          
        String mensaje = construirMensaje(valores, 1);
        mensaje = (mensaje.substring(0, 1).toUpperCase()+mensaje.substring(1).toLowerCase());
        Nodo<E> nodoPreg = new Nodo((E)mensaje);
        if(valores[0].equals("#P")){
            nodoPreg.setLeft((sc.hasNext())? construirArbolGenio(sc) : null);
            nodoPreg.setRight((sc.hasNext())? construirArbolGenio(sc) : null);
            return nodoPreg;
        }
        else if(valores[0].equals("#R")){
            return nodoPreg;
        }
        return null;
    }
    
    /**
     * Método que se encarga de construir un mensaje de String a partir de un
     * arreglo de String.
     * @param valores Recibe como parámetro un array de String.
     * @param ind Recibe el índice desde donde empezará a recorrer el arreglo
     * que será construido como mensaje.
     * @return Devuelve el mensaje construido.
     */
    private String construirMensaje(String[] valores, int ind){
        StringBuilder sb = new StringBuilder();
        for(int i=ind; i<valores.length; i++){
            String str = valores[i];
            sb.append(str);
            sb.append(" ");
        }
        return sb.toString();
    }
    
    /**
     * Método que reincia la ubicación del nodo iterativo dentro del arbol
     * @return Devuelve el contenido del nodo root.
     */
    public E reiniciarNodoActual(){
        this.nodoActual = this.root;
        return this.nodoActual.getData();
    }
    
    /**
     * Método que itera el arbol de rama en rama hacia la izquierda
     * @return Devuelve null si no tiene más ramas o devuelve el contenido
     * si aún así al moverse se posiciona en una hoja o nodo intermedio.
     */
    public E iterarIzquierda(){
        if(this.nodoActual!=null){
            this.nodoActual = this.nodoActual.getLeft();
            return (this.nodoActual!=null)? this.nodoActual.getData() : null;
        }
        return null;
    }
    
    /**
     * Método que itera el arbol de rama en rama hacia la derecha
     * @return Devuelve null si no tiene más ramas o devuelve el contenido
     * en donde se encuentra luego de ser iterado a la derecha.
     */
    public E iterarDerecha(){
        if(this.nodoActual!=null){
            this.nodoActual = this.nodoActual.getRight();
            return (this.nodoActual!=null)? this.nodoActual.getData() : null;
        }
        return null;
    }

    /**
     * Método que ingresa al nodo actual un hijo, ya sea en su posicion 
     * izquierda o derecha.
     * @param data Recibe como parámetro la data del nodo que va a ser añadido
     * @param right Recibe true si el nodo será añadido a la derecha del nodo
     * actual, o false si el nodo será añadido a la izquierda del nodo actual.
     * @return Retorna true si el ingreso fué existoso o falso si es lo contario.
     */
    public boolean setHijosNodeActual(E data, boolean right){
        if(data!=null){
            Nodo<E> nodo = new Nodo<>(data);
            if(right)
                this.nodoActual.setRight(nodo);
            else 
                this.nodoActual.setLeft(nodo);
            return true;
        }
        return false;
    }
    
    /**
     * Método que obtiene información del nodo actual
     * @return Devuelve el contenido del nodo actual donde se encuentra el iterador
     * o null si el nodo actual es nulo.
     */
    public E getDataNodoActual(){
        return (this.nodoActual!=null)? this.nodoActual.getData() : null;
    }
    
    /**
     * Método que obtiene información del hijo derecho del nodo actual
     * @return Devuelve el contenido del nodo derecho al actual donde se 
     * encuentra el iterador o null si este es nulo.
     */
    public E getDataRightNodeActual(){
        return (this.nodoActual!=null)? this.nodoActual.getRight().getData() : null;
    }
    
    /**
     * Método que obtiene información del hijo izquierdo del nodo actual
     * @return Devuelve el contenido del nodo izquierdo al actual donde se 
     * encuentra el iterador o null si este es nulo.
     */
    public E getDataLeftNodeActual(){
        return (this.nodoActual!=null)? this.nodoActual.getLeft().getData() : null;
    }
    
    /**
     * Método que verifica si el nodo actual es hoja o no
     * @return Devuelve true si el nodo actual es hoja o falso si es lo contrario.
     */
    public boolean isHojaNodoActual(){
        return this.nodoActual.isHoja();
    }

    /**
     * Método que obtiene el nodo donde actualmente se encuentra el explorador
     * del árbol.
     * @return Devuelve el nodo en la posición actual.
     */
    public Nodo<E> getNodoActual() {
        return nodoActual;
    }
    
    /**
     * Método que modifica el nodo actual.
     * @param data Recibe como parámetro la data nueva del nodo actual.
     */
    public void setDataNodoActual(E data){
        nodoActual.setData(data);
    }
    
    /**
     * Método que guarda la información obtenida en el juego dentro de un
     * archivo txt.
     */
    public void guardarTxtArbolPreOrden(){
        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(PATH_DATOS),"UTF-8"))){            
            guardarTxtArbolPreOrden(root, writer);
        }
        catch(IOException e){
            System.out.println("Hubo un error al momento de escribir el archivo: "
                    +e.getMessage());
        }
    }
    
    /**
     * Método auxiliar recursivo que ayuda en guardar la información del arbol
     * de manera recursivo.
     * @param parent Recibe como parámetro un Nodo<E> dondé servirá para el
     * método recursivo.
     * @param writer Recibe como parámetro un BufferedWriter que servirá para la
     * escritura del archivo, línea a línea.
     * @throws IOException Lanza una excepción que se genera cuando no se encuentra
     * un archivo.
     */
    private void guardarTxtArbolPreOrden(Nodo<E> parent, BufferedWriter writer) throws IOException{
        if(parent!=null){
            if(parent.isHoja()){
                writer.write("#R "+(String)parent.getData());                
            }
            else{
                writer.write("#P "+(String)parent.getData());
            }
            writer.newLine();
            guardarTxtArbolPreOrden(parent.getLeft(), writer);
            guardarTxtArbolPreOrden(parent.getRight(), writer);
        }
    }
}
