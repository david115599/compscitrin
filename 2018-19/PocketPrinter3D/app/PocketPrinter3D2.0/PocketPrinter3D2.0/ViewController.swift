//
//  ViewController.swift
//  PocketPrinter3D2.0
//
//  Created by David Bershadsky on 6/11/19.
//  Copyright © 2019 David Bershadsky and Leah Teichholtz. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

  
    @IBOutlet weak var pp3dpicture: UIImageView!

    @IBOutlet var title0: UILabel!
    @IBOutlet weak var subtitle: UILabel!
    @IBOutlet weak var right: UIButton!
    
    @IBOutlet weak var slicermovie: UIImageView!
    @IBOutlet weak var left: UIButton!
    @IBOutlet weak var fdm: UITextView!
    @IBOutlet weak var sla: UITextView!
    @IBOutlet weak var pg1content: UITextView!
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }
    var pageindex = 0
    @IBAction func nextpage(_ sender: Any) {
        if(pageindex == 0){
            pp3dpicture.isHidden = true
            pg1content.isHidden = true
            left.isHidden = false
            fdm.isHidden = false
           
        }
        if(pageindex == 1){
            fdm.isHidden = true
            sla.isHidden = false
            
        }
        if(pageindex == 2){
            sla.isHidden = true
            slicermovie.isHidden = false
            
        }
        if(pageindex != 3){
                pageindex+=1}
    
        print(pageindex);
    }
     
    @IBAction func previouspage(_ sender: Any) {
        if(pageindex == 1){
            pp3dpicture.isHidden = false
            pg1content.isHidden = false
            left.isHidden = true
            fdm.isHidden = true
        }
        if(pageindex == 2){
            fdm.isHidden = false
            sla.isHidden = true
        }
        if(pageindex == 3){
            sla.isHidden = false
            slicermovie.isHidden = true
            
        }
        if(pageindex != 0){
            pageindex-=1}
        print(pageindex);
    }
    

    
}

