
/******************************************************************
 *
 *   NAME: Fareen Samad      SECTION: COMP 272-001
 *
 *   This java file contains the problem solutions for the methods lastBoulder,
 *   showDuplicates, and pair methods. You should utilize the Java Collection
 *   Framework for these methods.
 *
 ********************************************************************/

import java.util.*;
import java.util.PriorityQueue;

public class ProblemSolutions {

    /**
     * Priority Queue (PQ) Game
     *
     * PQ1 Problem Statement:
     * -----------------------
     *
     * You are given an array of integers of boulders where boulders[i] is the
     * weight of the ith boulder.
     *
     * We are playing a game with the boulders. On each turn, we choose the heaviest
     * two boulders and smash them together. Suppose the heaviest two boulders have
     * weights x and y. The result of this smash is:
     *
     * If x == y, both boulders are destroyed, and
     * If x != y, the boulder of weight x is destroyed, and the boulder of
     * weight y has new weight y - x.
     *
     * At the end of the game, there is at most one boulder left.
     *
     * Return the weight of the last remaining boulder. If there are no boulders
     * left, return 0.
     *
     *
     * Example 1:
     *
     * Input: boulders = [2,7,4,1,8,1]
     * Output: 1
     * Explanation:
     * We combine 7 and 8 to get 1 so the list converts to [2,4,1,1,1] then,
     * we combine 2 and 4 to get 2 so the list converts to [2,1,1,1] then,
     * we combine 2 and 1 to get 1 so the list converts to [1,1,1] then,
     * we combine 1 and 1 to get 0 so the list converts to [1] then that's the
     * value of the last stone.
     *
     * Example 2:
     *
     * Input: boulders = [1]
     * Output: 1
     *
     *
     *
     * RECOMMENDED APPROACH
     *
     * Initializing Priority Queue in reverse order, so that it gives
     * max element at the top. Taking top Elements and performing the
     * given operations in the question as long as 2 or more boulders;
     * returning the 0 if queue is empty else return pq.peek().
     */

    public static int lastBoulder(int[] boulders) {

        // Max-Heap priority queue created to store the boulders in reverse order
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        // Add all the boulders to the priority queue
        for (int boulder : boulders) {
            pq.add(boulder);
        }

        // While there are 2 or more boulders in the queue
        while (pq.size() > 1) {
            int x = pq.poll();
            int y = pq.poll();

            // If the weight of x and y are not equal, add the difference back to the queue
            if (x != y) {
                pq.add(x - y);
            }
        }

        // If there are no boulders left, return 0; else return the weight of last
        // boulder
        if (pq.isEmpty()) {
            return 0;
        } else {
            return pq.peek();
        }

    } // end of lastBoulder method

    /**
     * Method showDuplicates
     *
     * This method identifies duplicate strings in an array list. The list
     * is passed as an ArrayList<String> and the method returns an ArrayList<String>
     * containing only unique strings that appear more than once in the input list.
     * This returned array list is returned in sorted ascending order. Note that
     * this method should consider case (strings are case-sensitive).
     *
     * For example, if the input list was: "Lion", "Dog", "Cat", "Dog", "Horse",
     * "Lion", "CAT"
     * the method would return an ArrayList<String> containing: "Dog", "Lion"
     *
     * @param input an ArrayList<String>
     * @return an ArrayList<String> containing only unique strings that appear
     *         more than once in the input list. They will be in ascending order.
     */

    public static ArrayList<String> showDuplicates(ArrayList<String> input) {

        // Create a new ArrayList to store the result
        ArrayList<String> result = new ArrayList<>();

        // Create a new HashSet to store the unique strings
        HashSet<String> unique = new HashSet<>();

        // Create a new HashSet to store the duplicate strings
        HashSet<String> duplicates = new HashSet<>();

        // Iterate through the input list
        for (String set : input) {
            // If the string is not unique, add it to the duplicate set
            if (!unique.add(set)) {
                duplicates.add(set);
            }
        }

        // Add the duplicate strings to the result list
        for (String set : duplicates) {
            result.add(set);
        }

        // Sort the result list in ascending order
        Collections.sort(result);

        return result; // Return the result list

    } // end of showDuplicates method

    /**
     * Finds pairs in the input array that add up to k.
     *
     * @param input Array of integers
     * @param k     The sum to find pairs for
     * 
     * @return an ArrayList<String> containing a list of strings. The ArrayList
     *         of strings needs to be ordered both within a pair, and
     *         between pairs in ascending order. E.g.,
     *
     *         - Ordering within a pair:
     *         A string is a pair in the format "(a, b)", where a and b are
     *         ordered lowest to highest, e.g., if a pair was the numbers
     *         6 and 3, then the string would be "(3, 6)", and NOT "(6, 3)".
     *         - Ordering between pairs:
     *         The ordering of strings of pairs should be sorted in lowest to
     *         highest pairs. E.g., if the following two string pairs within
     *         the returned ArraryList, "(3, 6)" and "(2, 7), they should be
     *         ordered in the ArrayList returned as "(2, 7)" and "(3, 6 )".
     *
     *         Example output:
     *         If the input array list was {2, 3, 3, 4, 5, 6, 7}, then the
     *         returned ArrayList<String> would be {"(2, 7)", "(3, 6)", "(4, 5)"}
     *
     *         HINT: Considering using any Java Collection Framework ADT that we
     *         have used
     *         to date, though HashSet. Consider using Java's "Collections.sort()"
     *         for final
     *         sort of ArrayList before returning so consistent answer. Utilize
     *         Oracle's
     *         Java Framework documentation in its use.
     */

    public static ArrayList<String> pair(int[] input, int k) {

        // New ArrayList to store the result
        ArrayList<String> result = new ArrayList<>();

        // New HashSet to store the unique integers
        HashSet<Integer> unique = new HashSet<>();

        // New HashSet to store the pairs
        HashSet<String> pairs = new HashSet<>();

        // Loop through the input array
        for (int i : input) {
            // Calculate difference between the sum and the current integer
            int diff = k - i;

            // If the difference is in the unique set, add the pair to the pairs set
            if (unique.contains(diff)) {
                pairs.add("(" + Math.min(i, diff) + ", " + Math.max(i, diff) + ")");
            }

            // Add the integer to the unique set
            unique.add(i);
        }

        // Add the pairs to the result list
        for (String set : pairs) {
            result.add(set);
        }

        // Sort the result list in ascending order
        Collections.sort(result);

        // Return the result list
        return result;

    } // end of pair method

}