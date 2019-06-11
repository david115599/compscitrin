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
    @IBOutlet weak var left: UIButton!
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }
    @IBAction func nextpage(_ sender: Any) {
        pp3dpicture.isHidden = true
    }
     
    @IBAction func previouspage(_ sender: Any) {
        pp3dpicture.isHidden = false
    }
    
}

