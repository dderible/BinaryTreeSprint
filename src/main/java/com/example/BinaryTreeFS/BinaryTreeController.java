package com.example.BinaryTreeFS;
import com.example.BinaryTreeFS.BinaryTreeRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@Controller
public class BinaryTreeController {

    @Autowired
    private BinaryTreeRepository binaryTreeRepository;

    private BinarySearchTree bst = new BinarySearchTree();

    @GetMapping("/enter-numbers")
    public String showInputForm() {
        return "enter-numbers";
    }

    @PostMapping("/process-numbers")
    @ResponseBody
    public java.util.Map<String, Object> processNumbersJson(@RequestParam("numbers") String numbers) {
        BinarySearchTree bstJson = new BinarySearchTree();
        String[] numArray = numbers.split(",");
        for (String num : numArray) {
            bstJson.insert(Integer.parseInt(num.trim()));
        }

        BinaryTreeRecord binaryTreeRecord = new BinaryTreeRecord();
        binaryTreeRecord.setInputNumbers(numbers);

        binaryTreeRecord.setTreeStructure(bstJson.toJson());
        binaryTreeRepository.save(binaryTreeRecord);

        return bstJson.toJsonRec(bstJson.getRoot());
    }


    @GetMapping("/previous-trees")
    public String showPreviousTrees(Model model) {
        List<BinaryTreeRecord> records = binaryTreeRepository.findAll();
        model.addAttribute("records", records);
        return "previous-trees"; }
}