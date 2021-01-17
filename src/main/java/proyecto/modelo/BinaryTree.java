/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proyecto.modelo;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.List;
import java.util.Objects;

/**
 * 
 * @author KevinChevez
 */
public class BinaryTree<E> {
    private Node<E> root;
    
    /**
     * Node queda vacio
     */
    public BinaryTree(){
    }
    
    /**
     * Inner class donde se agrega el nodo y su inf.
     * @param <E> 
     */
    private class Node<E>{
        private E data;
        private Node<E> right;
        private Node<E> left;
        
        public Node(E data){
            this.data = data;
        }
        
        private boolean isHoja(){
            return (right == null && left== null);
        }
        
        private boolean isComplete(){
            return (left!=null && right!=null);
        }
        
    }
    
    /**
     * Metodo que verifica si el arbol está vacio
     * @return true si el arbol está vacio y false, si no lo está
     */
    public boolean isEmpty(){
        return root == null;
    }
    
    public boolean add(E child, E parent){
        Node<E> nchild = new Node<>(child);
        if(isEmpty() && parent == null){
            root = nchild;
            return true;
        }
        //add(B,A)
        Node<E> nParent = searchNode(parent);
        Node<E> nChildExist = searchNode(child);
        if(nChildExist == null && nParent != null){
            if(nParent.left == null){
                nParent.left = nchild; 
                return true;
            }
            else if(nParent.right == null){
                nParent.right =  nchild;
                return true;
            }
        }
        return false;
    }     
    
    public boolean remove(E data){
        Node<E> parent = searchParent(data);
        if(parent == null){
            return false;
        }
        if(parent.left!=null && parent.left.data.equals(data)){
            parent.left = null;
        }
        else{
            parent.right = null;
        }
        return true;
    }
    private Node<E> searchParent(E data){
        return searchParent(data, root);
    }
    private Node<E> searchParent(E data, Node<E> n){
        if(n == null){
            return n;
        }
        else if((n.left!=null && n.left.data.equals(data)) || (n.right!= null && n.right.data.equals(data))){
            //System.out.println("Devuelve el arbol p: "+n.data);
            return n;
        }
        else{ //hay que seguir descendiendo
            Node<E> restultLeft = searchParent(data, n.left);
            if(restultLeft!=null) return restultLeft;
            return searchParent(data, n.right);
        }
    }
    
    private Node<E> searchNode(E data){
        return searchNode(data, root);
    }
    
    /**
     * Funcion que ayuda a la recursividad y envia Nodo que irá cambiando por etapa
     * @param data Recibe la data a buscar
     * @param p Recibe el nodo a buscar en seccion
     * @return Devuelve el nodo que encontró y null si no encontró
     */
    private Node<E> searchNode(E data, Node<E> p){
        //Caso base 1
        if(p==null){
            return null; //tambien puede retornar p
        }
        //Caso base 2
        else if(p.data.equals(data)){
            return p; // devuelve el nodo investigado
        }
        //Caso recursivo
        else{
            //De esta manera busca en todo el arbol
//            Node<E> restultLeft = searchNode(data, p.left);
//            Node<E> restultRigth = searchNode(data, p.rigth);
//            /**
//            if(restultLeft != null){
//                return restultLeft;
//            }else{
//                return restultRigth;
//            }
//            */
//            return (restultLeft!=null)?restultLeft:restultRigth; //Funcion ternaria hace lo mismo que el if y else
            
            //De esta manera cuando encuentre la respuesta,
            Node<E> restultLeft = searchNode(data, p.left);
            if(restultLeft!=null) return restultLeft;
            return searchNode(data, p.right);
        }
    }
    
    public boolean add(E parent, E data, boolean insertIzq){
        Node<E> hijo = new Node<>(data);
        if(parent == null && isEmpty()){
            root = hijo;
            return true;
        }
        Node<E> nParent = searchNodo(parent);
        if(nParent==null){
            return false;
        }
        else if(nParent.isComplete()){
            return false;
        }        
        else if(nParent.isHoja()){
            if(insertIzq)
                nParent.left = hijo;
            else
                nParent.right = hijo;
        }
        else{
            if(insertIzq)
                nParent.left = (nParent.left==null)? hijo:nParent.left;
            else
                nParent.right = (nParent.right==null)? hijo:nParent.right;
        }
        return true;
    }
    
    public Node<E> searchNodo(E data){
        return (data!=null)? searchNodo(data, root) : null;
    }
    private Node<E> searchNodo(E data, Node<E> n){
        if(n == null){
            return null;
        }
        else if(n.data.equals(data) && !n.isComplete()){
            return n;
        }
        else{
            Node<E> izqResult = searchNodo(data, n.left);
            return (izqResult!=null)? izqResult : searchNodo(data, n.right);
        }
    }
    
    public void anchura(){
        if(!isEmpty()){
            Queue<Node<E>> cola = new LinkedList<>();
            cola.offer(root);
            while(!cola.isEmpty()){
                Node<E> n = cola.poll();
                System.out.print(n.data+" ");
                if(n.left!=null) cola.offer(n.left);
                if(n.right!=null) cola.offer(n.right);
            }
        }
        System.out.println("");
    }
    
    public void preOrden(){
        preOrden(root);
    }
    private void preOrden(Node<E> parent){
        if(parent!=null){
            System.out.print(parent.data+" ");
            preOrden(parent.left);
            preOrden(parent.right);
        }
    }
    
    public void inOrden(){
        inOrden(root);
    }
    private void inOrden(Node<E> parent){
        if(parent!=null){
            inOrden(parent.left);
            System.out.print(parent.data+" ");
            inOrden(parent.right);
        }
    }
    
    public void postOrden(){
        postOrden(root);
    }
    private void postOrden(Node<E> parent){
        if(parent!=null){
            postOrden(parent.left);
            postOrden(parent.right);
            System.out.print(parent.data+" ");
        }
    }
    
    public int size(){
        return size(root);
    }
    private int size(Node<E> n){
        if(n == null){
            return 0;
        }
        return 1+size(n.left)+size(n.right);
    }
    
    public int height(){
        return height(root);
    }
    private int height(Node<E> n){
        if(n == null){
            return 0;
        }
//        int v1 = 1+height(n.left);
//        int v2 = 1+height(n.rigth);
//        if(v1>v2){
//            return v1;
//        }
//        return v2;
        return 1 + Math.max(height(n.left), height(n.right));
    }
    
    public int countLeaves(){
        return countLeaves(root);
    }
    private int countLeaves(Node<E> n){
        if(n == null){
            return 0;
        }
        else if((n.left == null) && (n.right==null)){
            return 1;
        }
        else{
            return countLeaves(n.left)+countLeaves(n.right);
        }
    }
    
    public boolean isFull(){
        return isFull(root);
    }
    private boolean isFull(Node<E> n){
        if(n == null){
            return true;
        }
        if((n.left == null && n.right ==null) || (n.left == null && n.right == null)){
            return false;
        }
        return isFull(n.left) && isFull(n.right) && height(n.left)== height(n.right);
    }
    
    public String decision(List<String> respuestas){     
        Iterator<String> it = respuestas.iterator();
        return (respuestas!=null)? decision(it, (Node<String>)root) : "No hay Lista";
    }
    private String decision(Iterator<String> it, Node<String> parent){
        boolean tiene=it.hasNext();
        String valor = (it.hasNext())? it.next() : null;
        boolean va=valor!=null;
        boolean derecha = (valor!=null)? (valor.toLowerCase().equals("yes")) : false;
        if(parent == null || valor==null){
            return "INCERTIDUMBRE";
        }
        else if(derecha && parent.right.isHoja()){
            return parent.right.data;
        }
        else if(!derecha && parent.left.isHoja()){
            return parent.left.data;
        }
        else{
            return (derecha)? decision(it, parent.right): decision(it, parent.left);
        }
    }
    
    public boolean addMorse(E child, List<String> direcciones){
        this.root = (this.root == null)? (Node<E>)(new Node<String>("INICIO")) : this.root;
        Iterator<String> it = direcciones.iterator();
        if(child!=null && direcciones!=null){
            addMorse(child, it, root);
            return true;
        }
        return false;    
    }
    private Node<E> addMorse(E child, Iterator<String> it, Node<E> parent){
        Node<E> nodoHijo = new Node<>(child);
        Node<String> nodoVacio =  new Node<>("*");
        String direccion = (it.hasNext())? it.next() : null;
        if((direccion!=null)&& direccion.equals("-")){
            if(parent.left==null){
                parent.left = (it.hasNext())? addMorse(child, it, (Node<E>)nodoVacio) : nodoHijo;
            }
            else if(!parent.left.isHoja()){
                parent.left = (it.hasNext())? addMorse(child, it, parent.left) : nodoHijo;
            }
        }
        else if((direccion!=null)&& direccion.equals(".")){
            if(parent.right==null){
                parent.right = (it.hasNext())? addMorse(child, it, (Node<E>)nodoVacio) : nodoHijo;
            }
            else if(!parent.right.isHoja()){
                parent.right = (it.hasNext())? addMorse(child, it, parent.right) : nodoHijo;
            }
        }                       
        return parent;
    }
    
    public String codifyMorse(List<String> codigos){
        Iterator<String> it = codigos.iterator();
        return codifyMorse(it, root);
    }
    private String codifyMorse(Iterator<String> it, Node<E> parent){
        StringBuilder resp = new StringBuilder();        
        if(parent != null){
            if(parent.isHoja()){
                resp.append(parent.data);
                resp.append(codifyMorse(it, root));
            }
            String code = (it.hasNext())? it.next() : null;
            if(code!=null && code.equals("-")){
                resp.append(codifyMorse(it, parent.left));
            }
            else if(code!=null && code.equals(".")){
                resp.append(codifyMorse(it, parent.right));
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
    private List<E> equiposDerrotadosFases (Node<E> parent, Integer lvlArb, Integer lvlAct){
        List<E> resp = new ArrayList<>();
        if(parent!=null){
            lvlAct+=1;
            if(lvlArb < lvlAct) resp.add(parent.right.data);
            if(!parent.left.isHoja()){
                resp.addAll(equiposDerrotadosFases(parent.left, lvlArb, lvlAct));
            }
            if(!parent.right.isHoja()){
                resp.addAll(equiposDerrotadosFases(parent.right, lvlArb, lvlAct));
            }
        }
        return resp;
    }
    
    public List<E> equiposEliminados(E seleccion){
        return equiposEliminados(seleccion, root);
    }
    private List<E> equiposEliminados(E seleccion, Node<E> parent){
        List<E> resp = new ArrayList<>();
        if((seleccion!=null && parent!=null) && !parent.isHoja()){ 
            if( parent.data.equals(seleccion)){
                resp.add(parent.right.data);
                resp.addAll(equiposEliminados(seleccion, parent.left));
            }
            else{ // ayudo a evitar la búsqueda a ciegas
                if(parent.left.data.equals(seleccion)){
                    resp.addAll(equiposEliminados(seleccion, parent.right));
                }
                else if(parent.left.data.equals(seleccion)){
                    resp.addAll(equiposEliminados(seleccion, parent.left));
                }
                else{
                    resp.addAll(equiposEliminados(seleccion, parent.left));
                    resp.addAll(equiposEliminados(seleccion, parent.right));
                }
            }
        }
        return resp;
    }
    
    public boolean isBST(){
        Comparator<E> comparador = (E o1, E o2) -> o1.hashCode()-o2.hashCode();
        return isBST(root, comparador);
    }
    private boolean isBST(Node<E> parent, Comparator<E> comparador){
        if(parent==null){
            return true;
        }
        if(parent.isComplete()){
            return (comparador.compare(parent.data, parent.left.data)>0 
                    && comparador.compare(parent.data, parent.right.data)<0 
                    && isBST(parent.left, comparador)
                    && isBST(parent.right, comparador));
        }
        else if(!parent.isHoja()){
                return (parent.left!=null)? 
                        (comparador.compare(parent.data, parent.left.data)>0 && isBST(parent.left, comparador))
                        :(comparador.compare(parent.data, parent.right.data)<0 && isBST(parent.right, comparador));
        }
        else{// si es hoja
            return true;
        } 
    }
    
    public void crearArbolHeap(int inicio, int n){
        root = (Node<E>)crearArbolHeap(inicio, n, root);
    }
    private Node<E> crearArbolHeap(int inicio, int n, Node<E> parent) {
        if(n>0){
            Node<Integer> raiz = new Node<>(inicio);
            if(parent == null){
                parent = (Node<E>)raiz;
            }
            parent.left = crearArbolHeap((inicio*2)+1, n-1, parent.left);
            parent.right = crearArbolHeap((inicio*2)+2, n-1, parent.right);
        }
        return parent;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.root);
        return hash;
    }
    
    //-----------------------------------TALLER-----------------------------------
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
    private boolean equals(Node<E> thisParent, Node<E> otherParent){        
        if(thisParent == null && otherParent==null){
            return true;
        }
        if(thisParent != null && otherParent!=null){
            return thisParent.data.equals(otherParent.data) &&
                    equals(thisParent.left, otherParent.left) &&
                    equals(thisParent.right, otherParent.right);
        }
        return false;
    }
    
}
