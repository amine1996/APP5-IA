##Les Fous-Fous - Partie 1


#####Question 1
Pour modéliser le plateau de jeu du jeu "Les Fous-Fous", le plus simple serait de faire un tableau de taille 8x8. Chaque case contiendrait une valeur assignée à une enumération contenant les valeurs suivantes :
>	**Enum case :**

>	Empty : 0
>	White : 1
>	Black : 2
>	Useless : 3 (Cases inatteignables car déplacement en diagonale)


* Avantages : SImplification du traitement du plateau (sauvegarde, visualisation...)
* Inconvénient : Prend plus de place de mémoire qu'un plateau optimisé.

#####Question 2


Pour détecter si une partie est finie, un des joueurs doit avoir un score égal à 16 (tous les pions de l'adversaire).

#####Question 3

Les paramètres sources de difficulté dans ce jeu sont les contraintes imposées par celui-ci (Etre obligé de prendre, être obligé de menacer...). Le facteur de branchement maximal est de (en début de partie) :

>	9 pions qui peuvent bouger dans 4 directions
>	6 pions qui peuvent bouger dans 2 directions
>	1 pion qui peut bouger dans 1 directions

On a un total de 49 possibilités en début de partie.

#####Question 4

Il n'existe pas de coups imparable, le meilleur coup étant de prendre un pion adverse sans être menacé par un pion ennemi ensuite.

#####Question 5

3 critères pour une heuristique :

>	Avoir le meilleur score après N coups
>	Est-ce que le coup joué met un pion danger
>	Si c'est à notre tour de jouer, est ce que l'ennemi à un pion menacé

#####Question 6

#####Question 7