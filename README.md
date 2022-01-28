Technical Interview, (Technical Lead Java)

Como ejecutar le programa del ejercicio 10:

Compilar

```
javac src/com/test/test.java
```

Ejecutar

```
java -classpath src com.test.Main < input.txt
```
o
```
java -classpath src com.test.Main < input2.txt
```
o
```
java -classpath src com.test.Main < input3.txt
```

Respuestas

1. Opcion C - el metodo que se intenta extender es final en la clase base
2. Opcion A
3. Opcion C - el array esta mal declarado deberia ser algo como         
    ```
        int[] arr = new int[2];
    ```
4. Opcion B
5. Opcion A
6. Opcion A - Para que compile, el bloque que captura Exception deberia estar ultima.
7. Opcion D
8. Opcion A - Los argumento pasan como valor, solo se esta editando la copia local en el metodo swap
9. El codigo que va en el bloque seria algo como esto
    ```
    List<Employee> employees = MaximumUsingStreamMain.createEmployeeList();
    System.out.println(employees.stream().map((Employee employee) -> employee.getName()).collect(Collectors.joining(", ")));
    ```
10. Estructuras de datos que use:
    - *Matriz de adjacencia:* esta la use para marcar marcar el color de los bordes
    - *Lista de adjacencia:* esta la use para llevar registro de que nodo se conecta con que otro nodo
    - Tambien use algunas listas para llevar registro de que nodos fueron visitados al recorrer el grafo y que camino estaba siguiendo
    - El algoritmo que seleccione para recorrer el DFS (Depth First Search). 
    - Creo que puede tambien resolverse con BFS y no veo ninguna ventaja particular
    Complejidad temporal (V = vertices, E = aristas):
    - con respecto al agoritmo, BFS con listas de adjacencia tiene una complejidad temporal O(V+E) cuando se usa una lista de adjacencia.
    - mas alla de eso, la solucion en su conjunto genera y busca sobre todos los tripletes posibles, usando 3 bucles for, la complejidad seria O(V^3)
    - En este momento, no se me ocurre una forma simple de reducir el tiempo de ejecucion

