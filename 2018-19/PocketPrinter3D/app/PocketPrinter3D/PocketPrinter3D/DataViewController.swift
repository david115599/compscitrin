//
//  DataViewController.swift
//  PocketPrinter3D
//
//  Created by David Bershadsky on 6/10/19.
//  Copyright © 2019 David Bershadsky and Leah Teichholtz. All rights reserved.
//

import UIKit

class DataViewController: UIViewController {

    @IBOutlet weak var dataLabel: UILabel!
    var dataObject: String = ""


    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }

    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.dataLabel!.text = dataObject
    }


}

